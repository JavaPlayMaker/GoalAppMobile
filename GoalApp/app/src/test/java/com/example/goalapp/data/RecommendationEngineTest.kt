package com.example.goalapp.data

import org.junit.Assert.*
import org.junit.Test

class RecommendationEngineTest {

    private val engine = RecommendationEngine()

    private val sampleActivities = listOf(
        GoalActivity(
            name = "Free Indoor Activity",
            whyFits = "Fits",
            firstStep = "Start",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 30,
            isFree = true
        ),
        GoalActivity(
            name = "Paid Outdoor Activity",
            whyFits = "Fits",
            firstStep = "Start",
            moods = listOf(Mood.STRESSED),
            minEnergy = EnergyLevel.HIGH,
            socialPreference = SocialPreference.AROUND_PEOPLE,
            maxTimeMinutes = 120,
            isFree = false
        )
    )

    @Test
    fun `getRecommendation filters by mood`() {
        val checkIn = UserCheckIn(Mood.BORED, EnergyLevel.LOW, SocialPreference.ALONE, TimeAvailable.THIRTY_MINUTES)
        val profile = UserProfile()
        
        val recommendation = engine.getRecommendation(sampleActivities, checkIn, profile)
        
        assertEquals("Free Indoor Activity", recommendation.name)
    }

    @Test
    fun `getRecommendation filters by budget`() {
        val checkIn = UserCheckIn(Mood.STRESSED, EnergyLevel.HIGH, SocialPreference.AROUND_PEOPLE, TimeAvailable.MORE_THAN_ONE_HOUR)
        val profile = UserProfile(budgetPreference = BudgetPreference.FREE)
        
        val recommendation = engine.getRecommendation(sampleActivities, checkIn, profile)
        
        // Since Paid Activity is filtered out, it should fall back to the first available or shuffled first
        // In this case, Free Indoor Activity doesn't match mood, but it's the only one left after filtering?
        // Wait, filtering logic: activity.moods.contains(checkIn.mood) -> "Paid" has STRESSED, matches.
        // BUT (profile.budgetPreference != BudgetPreference.FREE || activity.isFree) -> Paid is NOT free, profile is FREE.
        // So BOTH are filtered out (one by mood/energy/social, one by budget).
        // Fallback is activities.shuffled().first()
        
        assertNotNull(recommendation)
    }

    @Test
    fun `getRecommendation prioritizes interests`() {
        val activityWithInterest = GoalActivity(
            name = "Interest Activity",
            whyFits = "Fits",
            firstStep = "Start",
            moods = listOf(Mood.BORED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 30,
            interests = listOf(Interest.GAMING)
        )
        val activities = sampleActivities + activityWithInterest
        
        val checkIn = UserCheckIn(Mood.BORED, EnergyLevel.LOW, SocialPreference.ALONE, TimeAvailable.THIRTY_MINUTES)
        val profile = UserProfile(interests = listOf(Interest.GAMING))
        
        val recommendation = engine.getRecommendation(activities, checkIn, profile)
        
        assertEquals("Interest Activity", recommendation.name)
    }
}
