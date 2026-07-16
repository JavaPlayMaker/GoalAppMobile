package com.example.goalapp.data

class RecommendationEngine {
    fun getRecommendation(
        activities: List<GoalActivity>,
        checkIn: UserCheckIn,
        profile: UserProfile
    ): GoalActivity {
        // Since specific filtering parameters were removed, we now provide 
        // a high-quality random suggestion from the curated list.
        return activities.shuffled().first()
    }
}
