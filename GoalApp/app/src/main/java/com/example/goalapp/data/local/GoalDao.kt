package com.example.goalapp.data.local

import androidx.room.*
import com.example.goalapp.data.*
import kotlinx.coroutines.flow.Flow

@Dao
interface GoalDao {
    // Journal
    @Query("SELECT * FROM journal_entries ORDER BY created_at DESC")
    fun getAllJournalEntries(): Flow<List<JournalEntry>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertJournalEntry(entry: JournalEntry): Long

    // Activities
    @Query("SELECT * FROM activity_logs ORDER BY startTime DESC")
    fun getAllActivityLogs(): Flow<List<ActivityLog>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertActivityLog(log: ActivityLog): Long

    // Habits
    @Query("SELECT * FROM habits")
    fun getAllHabits(): Flow<List<Habit>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHabit(habit: Habit): Long

    @Query("SELECT * FROM habit_logs WHERE habitId = :habitId")
    fun getHabitLogs(habitId: Int): List<HabitLog>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHabitLog(log: HabitLog): Long

    // Goals
    @Query("SELECT * FROM goals")
    fun getAllGoals(): Flow<List<Goal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGoal(goal: Goal): Long

    @Update
    fun updateGoal(goal: Goal): Int

    // Stats
    @Query("SELECT * FROM user_stats WHERE id = 'local_user' LIMIT 1")
    fun getUserStats(): Flow<UserStats?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserStats(stats: UserStats): Long
}
