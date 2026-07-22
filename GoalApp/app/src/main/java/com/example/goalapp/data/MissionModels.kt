package com.example.goalapp.data

import kotlinx.serialization.Serializable

@Serializable
enum class ActivityType {
    JOURNAL,
    GOAL,
    MISSION_COMPLETE
}

@Serializable
data class ActivityRecord(
    val timestamp: Long,
    val type: ActivityType,
    val points: Int,
    val description: String
)

data class DailyMission(
    val id: String,
    val title: String,
    val points: Int,
    val isCompleted: Boolean
)
