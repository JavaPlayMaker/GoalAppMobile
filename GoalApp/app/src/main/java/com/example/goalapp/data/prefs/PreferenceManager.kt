package com.example.goalapp.data.prefs

import android.content.Context
import android.util.Log
import androidx.core.content.edit
import com.example.goalapp.data.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class PreferenceManager(context: Context) {
    private val prefs = context.getSharedPreferences("goal_app_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_PROFILE_COMPLETED = "profile_completed"
        private const val KEY_LIVING_SITUATION = "living_situation"
        private const val KEY_SOCIAL_ENJOYMENT = "social_enjoyment"
        private const val KEY_EXERCISE_FREQUENCY = "exercise_frequency"
        private const val KEY_EMPLOYMENT_STATUS = "employment_status"
        private const val KEY_BUDGET_PREFERENCE = "budget_preference"
        private const val KEY_INTERESTS = "interests"
        private const val KEY_OBSTACLES = "obstacles"
        private const val KEY_FOCUS = "focus"
        private const val KEY_DONE_COUNT = "done_count"
        private const val KEY_FEEDBACK_SHOWN = "feedback_shown"
        private const val KEY_JOURNAL_REMINDER_ENABLED = "journal_reminder_enabled"
        private const val KEY_JOURNAL_REMINDER_TIME = "journal_reminder_time"
        private const val KEY_INACTIVITY_NOTIFICATION_ENABLED = "inactivity_notification_enabled"
        
        // New Mission and Points Keys
        private const val KEY_TOTAL_POINTS = "total_points"
        private const val KEY_LAST_MISSION_RESET_DATE = "last_mission_reset_date"
        private const val KEY_JOURNAL_MISSION_COMPLETED = "journal_mission_completed"
        private const val KEY_GOAL_MISSION_COMPLETED = "goal_mission_completed"
        private const val KEY_MY_STATS = "my_stats"
        private const val KEY_LEARN_PAGE_UNLOCKED = "learn_page_unlocked"
    }

    fun getTotalPoints(): Int = prefs.getInt(KEY_TOTAL_POINTS, 0)

    fun addPoints(points: Int) {
        val current = getTotalPoints()
        Log.d("PreferenceManager", "Adding $points points to $current")
        prefs.edit { putInt(KEY_TOTAL_POINTS, current + points) }
    }

    fun spendPoints(points: Int): Boolean {
        val current = getTotalPoints()
        return if (current >= points) {
            Log.d("PreferenceManager", "Spending $points points from $current")
            prefs.edit { putInt(KEY_TOTAL_POINTS, current - points) }
            true
        } else {
            Log.d("PreferenceManager", "Not enough points to spend $points (current: $current)")
            false
        }
    }

    fun isLearnPageUnlocked(): Boolean = prefs.getBoolean(KEY_LEARN_PAGE_UNLOCKED, false)

    fun setLearnPageUnlocked(unlocked: Boolean) {
        prefs.edit { putBoolean(KEY_LEARN_PAGE_UNLOCKED, unlocked) }
    }

    fun getLastMissionResetDate(): String = prefs.getString(KEY_LAST_MISSION_RESET_DATE, "") ?: ""

    fun setLastMissionResetDate(date: String) {
        prefs.edit { putString(KEY_LAST_MISSION_RESET_DATE, date) }
    }

    fun isJournalMissionCompleted(): Boolean = prefs.getBoolean(KEY_JOURNAL_MISSION_COMPLETED, false)

    fun setJournalMissionCompleted(completed: Boolean) {
        prefs.edit { putBoolean(KEY_JOURNAL_MISSION_COMPLETED, completed) }
    }

    fun isGoalMissionCompleted(): Boolean = prefs.getBoolean(KEY_GOAL_MISSION_COMPLETED, false)

    fun setGoalMissionCompleted(completed: Boolean) {
        prefs.edit { putBoolean(KEY_GOAL_MISSION_COMPLETED, completed) }
    }

    fun getMyStats(): List<ActivityRecord> {
        val json = prefs.getString(KEY_MY_STATS, "[]") ?: "[]"
        Log.d("PreferenceManager", "Retrieving Stats JSON: $json")
        return try {
            Json.decodeFromString(json)
        } catch (e: Exception) {
            Log.e("PreferenceManager", "Error decoding stats JSON", e)
            emptyList()
        }
    }

    fun addStatRecord(record: ActivityRecord) {
        val stats = getMyStats().toMutableList()
        stats.add(0, record) // Add to top
        val json = Json.encodeToString(stats)
        Log.d("PreferenceManager", "Saving Stats JSON: $json")
        prefs.edit { putString(KEY_MY_STATS, json) }
    }

    fun isJournalReminderEnabled(): Boolean = prefs.getBoolean(KEY_JOURNAL_REMINDER_ENABLED, false)

    fun setJournalReminderEnabled(enabled: Boolean) {
        prefs.edit { putBoolean(KEY_JOURNAL_REMINDER_ENABLED, enabled) }
    }

    fun getJournalReminderTime(): String = prefs.getString(KEY_JOURNAL_REMINDER_TIME, "20:00")!!

    fun setJournalReminderTime(time: String) {
        prefs.edit { putString(KEY_JOURNAL_REMINDER_TIME, time) }
    }

    fun isInactivityNotificationEnabled(): Boolean = prefs.getBoolean(KEY_INACTIVITY_NOTIFICATION_ENABLED, true)

    fun setInactivityNotificationEnabled(enabled: Boolean) {
        prefs.edit { putBoolean(KEY_INACTIVITY_NOTIFICATION_ENABLED, enabled) }
    }

    fun getDoneCount(): Int = prefs.getInt(KEY_DONE_COUNT, 0)

    fun incrementDoneCount() {
        prefs.edit { putInt(KEY_DONE_COUNT, getDoneCount() + 1) }
    }

    fun resetDoneCount() {
        prefs.edit { putInt(KEY_DONE_COUNT, 0) }
    }

    fun isFeedbackFlowShown(): Boolean = prefs.getBoolean(KEY_FEEDBACK_SHOWN, false)

    fun setFeedbackFlowShown(shown: Boolean) {
        prefs.edit { putBoolean(KEY_FEEDBACK_SHOWN, shown) }
    }

    fun isProfileCompleted(): Boolean {
        return prefs.getBoolean(KEY_PROFILE_COMPLETED, false)
    }

    fun saveUserProfile(profile: UserProfile) {
        prefs.edit {
            putBoolean(KEY_PROFILE_COMPLETED, true)
            putString(KEY_LIVING_SITUATION, profile.livingSituation.name)
            putString(KEY_SOCIAL_ENJOYMENT, profile.socialEnjoyment)
            putString(KEY_EXERCISE_FREQUENCY, profile.exerciseFrequency.name)
            putString(KEY_EMPLOYMENT_STATUS, profile.employmentStatus.name)
            putString(KEY_BUDGET_PREFERENCE, profile.budgetPreference.name)
            putStringSet(KEY_INTERESTS, profile.interests.map { it.name }.toSet())
            putStringSet(KEY_OBSTACLES, profile.obstacles.map { it.name }.toSet())
            putString(KEY_FOCUS, profile.focus.name)
        }
    }

    fun getUserProfile(): UserProfile? {
        if (!isProfileCompleted()) return null
        
        return try {
            UserProfile(
                livingSituation = LivingSituation.valueOf(prefs.getString(KEY_LIVING_SITUATION, LivingSituation.OTHER.name)!!),
                socialEnjoyment = prefs.getString(KEY_SOCIAL_ENJOYMENT, "Sometimes")!!,
                exerciseFrequency = ExerciseFrequency.valueOf(prefs.getString(KEY_EXERCISE_FREQUENCY, ExerciseFrequency.RARELY.name)!!),
                employmentStatus = EmploymentStatus.valueOf(prefs.getString(KEY_EMPLOYMENT_STATUS, EmploymentStatus.OTHER.name)!!),
                budgetPreference = BudgetPreference.valueOf(prefs.getString(KEY_BUDGET_PREFERENCE, BudgetPreference.NO_PREFERENCE.name)!!),
                interests = prefs.getStringSet(KEY_INTERESTS, emptySet())?.map { Interest.valueOf(it) } ?: emptyList(),
                obstacles = prefs.getStringSet(KEY_OBSTACLES, emptySet())?.map { Obstacle.valueOf(it) } ?: emptyList(),
                focus = GoalFocus.valueOf(prefs.getString(KEY_FOCUS, GoalFocus.FIND_DO.name)!!)
            )
        } catch (e: Exception) {
            null
        }
    }
}
