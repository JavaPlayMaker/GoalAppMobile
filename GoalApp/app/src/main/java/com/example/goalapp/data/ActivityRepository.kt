package com.example.goalapp.data

import kotlinx.coroutines.runBlocking

object ActivityRepository {
    private val engine = RecommendationEngine()
    private var cachedActivities: List<GoalActivity>? = null

    suspend fun getRecommendation(checkIn: UserCheckIn, profile: UserProfile): GoalActivity {
        val activities = if (cachedActivities == null) {
            try {
                val remote = SupabaseRepository.getActivities()
                if (remote.isEmpty()) {
                    // Seed if empty (for initial run)
                    SupabaseRepository.insertInitialActivities(ActivityDataSource.activities)
                    ActivityDataSource.activities
                } else {
                    cachedActivities = remote
                    remote
                }
            } catch (e: Exception) {
                // Fallback to local if network fails
                ActivityDataSource.activities
            }
        } else {
            cachedActivities!!
        }

        return engine.getRecommendation(
            activities,
            checkIn,
            profile
        )
    }
}
