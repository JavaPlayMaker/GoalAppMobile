package com.example.goalapp.data

object ActivityRepository {
    private val engine = RecommendationEngine()

    fun getRecommendation(checkIn: UserCheckIn, profile: UserProfile): GoalActivity {
        return engine.getRecommendation(
            ActivityDataSource.activities,
            checkIn,
            profile
        )
    }
}
