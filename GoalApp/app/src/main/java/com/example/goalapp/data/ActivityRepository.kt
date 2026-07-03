package com.example.goalapp.data

object ActivityRepository {
    private val activities = listOf(
        GoalActivity(
            name = "Take a short walk",
            whyFits = "Since you're feeling low-energy, this is an easy way to change your environment without asking too much of yourself.",
            firstStep = "Put on your shoes and walk for five minutes.",
            moods = listOf(Mood.TIRED, Mood.STRESSED, Mood.BORED, Mood.JUST_WANT_SOMETHING_TO_DO),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.EITHER,
            maxTimeMinutes = 15,
            interests = listOf(Interest.WALKING, Interest.NATURE)
        ),
        GoalActivity(
            name = "Sit in a park",
            whyFits = "Being in nature can help calm a restless mind and provides a gentle change of pace.",
            firstStep = "Find the nearest green space and just sit for 10 minutes.",
            moods = listOf(Mood.RESTLESS, Mood.STRESSED, Mood.LONELY),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.AROUND_PEOPLE,
            maxTimeMinutes = 30,
            interests = listOf(Interest.NATURE)
        ),
        GoalActivity(
            name = "Read a chapter of a book",
            whyFits = "Getting lost in a story is a great way to handle boredom or loneliness from the comfort of your home.",
            firstStep = "Pick up that book you started and read just five pages.",
            moods = listOf(Mood.BORED, Mood.LONELY, Mood.UNMOTIVATED),
            minEnergy = EnergyLevel.LOW,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 60,
            interests = listOf(Interest.READING)
        ),
        GoalActivity(
            name = "Organize one drawer",
            whyFits = "Completing a small, tangible task can provide a sense of accomplishment when you feel unmotivated.",
            firstStep = "Open one drawer and take everything out.",
            moods = listOf(Mood.UNMOTIVATED, Mood.RESTLESS, Mood.BORED),
            minEnergy = EnergyLevel.MEDIUM,
            socialPreference = SocialPreference.ALONE,
            maxTimeMinutes = 20
        ),
        GoalActivity(
            name = "Call a friend",
            whyFits = "Sometimes a quick chat is all it takes to shift your mood when you're feeling lonely or sad.",
            firstStep = "Open your contacts and scroll to a name that makes you smile.",
            moods = listOf(Mood.LONELY, Mood.SAD),
            minEnergy = EnergyLevel.MEDIUM,
            socialPreference = SocialPreference.AROUND_PEOPLE,
            maxTimeMinutes = 30,
            interests = listOf(Interest.MEETING_PEOPLE)
        ),
        GoalActivity(
            name = "Quick workout or stretch",
            whyFits = "Moving your body is a proven way to boost your mood and handle restlessness.",
            firstStep = "Do 10 jumping jacks or a simple overhead stretch.",
            moods = listOf(Mood.RESTLESS, Mood.UNMOTIVATED, Mood.JUST_WANT_SOMETHING_TO_DO),
            minEnergy = EnergyLevel.HIGH,
            socialPreference = SocialPreference.EITHER,
            maxTimeMinutes = 15,
            interests = listOf(Interest.EXERCISE)
        ),
        GoalActivity(
            name = "Visit a coffee shop",
            whyFits = "The gentle hum of a café can help you feel less alone without requiring active socialization.",
            firstStep = "Grab your wallet and walk to the nearest café.",
            moods = listOf(Mood.LONELY, Mood.BORED),
            minEnergy = EnergyLevel.MEDIUM,
            socialPreference = SocialPreference.AROUND_PEOPLE,
            maxTimeMinutes = 45,
            interests = listOf(Interest.COFFEE_SHOPS),
            isFree = false
        )
    )

    fun getRecommendation(checkIn: UserCheckIn, profile: UserProfile): GoalActivity {
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
