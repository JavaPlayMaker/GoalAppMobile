package com.example.goalapp.data

import android.content.Context
import com.example.goalapp.data.local.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class MainRepository(context: Context) {
    private val database = AppDatabase.getDatabase(context)
    private val dao = database.goalDao()
    
    // Pool of activities for suggestions
    private val activityPool = ActivityDataSource.activities
    private var lastRecommendedActivity: GoalActivity? = null

    // User Stats & Gamification
    val userStats: Flow<UserStats> = dao.getUserStats().map { it ?: UserStats() }

    suspend fun addPoints(points: Int) = withContext(Dispatchers.IO) {
        val currentStats = dao.getUserStats().firstOrNull() ?: UserStats()
        val newPoints = currentStats.total_points + points
        val newLevel = (newPoints / 1000) + 1
        
        val unlocksGames = currentStats.unlocked_games || newPoints >= 500
        val unlocksLearn = currentStats.unlocked_learn || newPoints >= 200

        dao.insertUserStats(currentStats.copy(
            total_points = newPoints,
            level = newLevel,
            unlocked_games = unlocksGames,
            unlocked_learn = unlocksLearn
        ))
    }

    // Journal
    fun getAllJournalEntries(): Flow<List<JournalEntry>> = dao.getAllJournalEntries()
    
    suspend fun saveJournalEntry(entry: JournalEntry) = withContext(Dispatchers.IO) {
        dao.insertJournalEntry(entry)
        addPoints(10)
    }

    // Activities
    fun getAllActivityLogs(): Flow<List<ActivityLog>> = dao.getAllActivityLogs()
    
    suspend fun logActivity(log: ActivityLog): Long = withContext(Dispatchers.IO) {
        val id = dao.insertActivityLog(log)
        addPoints(20) 
        id
    }

    // Habits
    fun getAllHabits(): Flow<List<Habit>> = dao.getAllHabits()
    suspend fun addHabit(habit: Habit) = withContext(Dispatchers.IO) {
        dao.insertHabit(habit)
    }
    suspend fun checkInHabit(log: HabitLog) = withContext(Dispatchers.IO) {
        dao.insertHabitLog(log)
        addPoints(5)
    }

    // Goals
    fun getAllGoals(): Flow<List<Goal>> = dao.getAllGoals()
    suspend fun addGoal(goal: Goal) = withContext(Dispatchers.IO) {
        dao.insertGoal(goal)
    }
    
    suspend fun completeGoal(goal: Goal) = withContext(Dispatchers.IO) {
        dao.updateGoal(goal.copy(isCompleted = true))
        addPoints(goal.pointsValue)
    }

    fun getSuggestedGoal(checkIn: UserCheckIn): GoalActivity {
        // Filter pool based on energy level for smarter suggestions
        val filteredPool = when (checkIn.energyLevel) {
            EnergyLevel.HIGH -> activityPool.filter { 
                it.name.contains("run", ignoreCase = true) || 
                it.name.contains("gym", ignoreCase = true) || 
                it.name.contains("bike", ignoreCase = true) ||
                it.name.contains("climbing", ignoreCase = true) ||
                it.name.contains("swim", ignoreCase = true)
            }
            EnergyLevel.LOW -> activityPool.filter { 
                it.name.contains("read", ignoreCase = true) || 
                it.name.contains("movie", ignoreCase = true) || 
                it.name.contains("cafe", ignoreCase = true) ||
                it.name.contains("sketch", ignoreCase = true) ||
                it.name.contains("museum", ignoreCase = true)
            }
            else -> activityPool // Medium energy gets everything
        }.ifEmpty { activityPool }

        // Pick a random one that isn't the same as the last one
        var suggestion = filteredPool.random()
        
        // Try to get a different one if possible
        if (suggestion == lastRecommendedActivity && filteredPool.size > 1) {
            suggestion = filteredPool.filter { it != lastRecommendedActivity }.random()
        }
        
        lastRecommendedActivity = suggestion
        return suggestion
    }
}
