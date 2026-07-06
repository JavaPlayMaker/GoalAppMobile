package com.example.goalapp.data

class RecommendationEngine {
    fun getRecommendation(
        activities: List<GoalActivity>,
        checkIn: UserCheckIn,
        profile: UserProfile
    ): GoalActivity {
        val filtered = activities.filter { activity ->
            // Mood match
            activity.moods.contains(checkIn.mood) &&
            // Energy check
            checkIn.energyLevel.ordinal >= activity.minEnergy.ordinal &&
            // Social match
            (activity.socialPreference == SocialPreference.EITHER || 
             checkIn.socialPreference == SocialPreference.EITHER ||
             activity.socialPreference == checkIn.socialPreference) &&
            // Time check
            activity.maxTimeMinutes <= checkIn.timeAvailable.minutes &&
            // Budget check: If user wants free only, filter out paid activities
            (profile.budgetPreference != BudgetPreference.FREE || activity.isFree) &&
            // Obstacle check: If user prefers home, filter out non-home activities (simplified)
            (!profile.obstacles.contains(Obstacle.PREFER_HOME) || activity.socialPreference == SocialPreference.ALONE)
        }

        // Weighted recommendation: prioritize user interests
        val highlyRelevant = filtered.filter { it.interests.any { interest -> profile.interests.contains(interest) } }
        
        return highlyRelevant.shuffled().firstOrNull() 
               ?: filtered.shuffled().firstOrNull() 
               ?: activities.shuffled().first()
    }
}
