package com.example.goalapp.data

import android.content.Context
import android.util.Log
import com.example.goalapp.data.prefs.PreferenceManager
import java.text.SimpleDateFormat
import java.util.*

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MissionManager(context: Context) {
    private val preferenceManager = PreferenceManager(context)
    private val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    private val scope = CoroutineScope(Dispatchers.IO)

    fun checkAndResetMissions() {
        val today = dateFormatter.format(Date())
        val lastReset = preferenceManager.getLastMissionResetDate()

        if (today != lastReset) {
            preferenceManager.setJournalMissionCompleted(false)
            preferenceManager.setGoalMissionCompleted(false)
            preferenceManager.setLastMissionResetDate(today)
            
            // Sync with Supabase if possible
            syncStatsToSupabase()
        }
    }

    private fun syncStatsToSupabase() {
        scope.launch {
            try {
                val customerId = "simulated-user-id" // Replace with real Auth ID
                SupabaseRepository.updateUserStats(
                    UserStats(
                        customer_id = customerId,
                        total_points = preferenceManager.getTotalPoints(),
                        last_mission_reset = preferenceManager.getLastMissionResetDate(),
                        journal_mission_completed = preferenceManager.isJournalMissionCompleted(),
                        goal_mission_completed = preferenceManager.isGoalMissionCompleted()
                    )
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun completeJournalMission() {
        Log.d("MissionManager", "completeJournalMission called")
        checkAndResetMissions()
        
        // Always record the activity
        preferenceManager.addStatRecord(
            ActivityRecord(
                timestamp = System.currentTimeMillis(),
                type = ActivityType.JOURNAL,
                points = if (!preferenceManager.isJournalMissionCompleted()) 50 else 0,
                description = "Completed Journal Entry"
            )
        )

        if (!preferenceManager.isJournalMissionCompleted()) {
            Log.d("MissionManager", "Journal mission NOT completed yet, awarding points")
            preferenceManager.setJournalMissionCompleted(true)
            preferenceManager.addPoints(50)
            syncStatsToSupabase()
            checkAllMissionsCompleted()
        } else {
            Log.d("MissionManager", "Journal mission already completed today, skipping points")
        }
    }

    fun completeGoalMission() {
        Log.d("MissionManager", "completeGoalMission called")
        checkAndResetMissions()
        
        // Always record the activity
        preferenceManager.addStatRecord(
            ActivityRecord(
                timestamp = System.currentTimeMillis(),
                type = ActivityType.GOAL,
                points = if (!preferenceManager.isGoalMissionCompleted()) 50 else 0,
                description = "Completed Goal Activity"
            )
        )

        if (!preferenceManager.isGoalMissionCompleted()) {
            Log.d("MissionManager", "Goal mission NOT completed yet, awarding points")
            preferenceManager.setGoalMissionCompleted(true)
            preferenceManager.addPoints(50)
            syncStatsToSupabase()
            checkAllMissionsCompleted()
        } else {
            Log.d("MissionManager", "Goal mission already completed today, skipping points")
        }
    }

    private fun checkAllMissionsCompleted() {
        if (preferenceManager.isJournalMissionCompleted() && preferenceManager.isGoalMissionCompleted()) {
            // Award bonus for completing all missions?
            // For now just add a record
            preferenceManager.addStatRecord(
                ActivityRecord(
                    timestamp = System.currentTimeMillis(),
                    type = ActivityType.MISSION_COMPLETE,
                    points = 0,
                    description = "All Daily Missions Completed!"
                )
            )
        }
    }

    fun getDailyMissions(): List<DailyMission> {
        checkAndResetMissions()
        return listOf(
            DailyMission(
                id = "journal",
                title = "Complete a Journal Entry",
                points = 50,
                isCompleted = preferenceManager.isJournalMissionCompleted()
            ),
            DailyMission(
                id = "goal",
                title = "Complete a Goal Check-in",
                points = 50,
                isCompleted = preferenceManager.isGoalMissionCompleted()
            )
        )
    }

    fun getTotalPoints(): Int = preferenceManager.getTotalPoints()
    
    fun spendPoints(points: Int): Boolean = preferenceManager.spendPoints(points)

    fun isLearnPageUnlocked(): Boolean = preferenceManager.isLearnPageUnlocked()

    fun unlockLearnPage() = preferenceManager.setLearnPageUnlocked(true)
    
    fun getMyStats(): List<ActivityRecord> = preferenceManager.getMyStats()
}
