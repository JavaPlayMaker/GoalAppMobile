package com.example.goalapp.data

import android.content.Context
import com.example.goalapp.data.prefs.PreferenceManager
import java.text.SimpleDateFormat
import java.util.*

class MissionManager(context: Context) {
    private val preferenceManager = PreferenceManager(context)
    private val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    fun checkAndResetMissions() {
        val today = dateFormatter.format(Date())
        val lastReset = preferenceManager.getLastMissionResetDate()

        if (today != lastReset) {
            preferenceManager.setJournalMissionCompleted(false)
            preferenceManager.setGoalMissionCompleted(false)
            preferenceManager.setLastMissionResetDate(today)
        }
    }

    fun completeJournalMission() {
        checkAndResetMissions()
        if (!preferenceManager.isJournalMissionCompleted()) {
            preferenceManager.setJournalMissionCompleted(true)
            preferenceManager.addPoints(50)
            preferenceManager.addActivityRecord(
                ActivityRecord(
                    timestamp = System.currentTimeMillis(),
                    type = ActivityType.JOURNAL,
                    points = 50,
                    description = "Completed Daily Journal"
                )
            )
            checkAllMissionsCompleted()
        }
    }

    fun completeGoalMission() {
        checkAndResetMissions()
        if (!preferenceManager.isGoalMissionCompleted()) {
            preferenceManager.setGoalMissionCompleted(true)
            preferenceManager.addPoints(50)
            preferenceManager.addActivityRecord(
                ActivityRecord(
                    timestamp = System.currentTimeMillis(),
                    type = ActivityType.GOAL,
                    points = 50,
                    description = "Completed Daily Goal Activity"
                )
            )
            checkAllMissionsCompleted()
        }
    }

    private fun checkAllMissionsCompleted() {
        if (preferenceManager.isJournalMissionCompleted() && preferenceManager.isGoalMissionCompleted()) {
            // Award bonus for completing all missions?
            // For now just add a record
            preferenceManager.addActivityRecord(
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
    
    fun getActivityHistory(): List<ActivityRecord> = preferenceManager.getActivityHistory()
}
