package com.example.goalapp.data

import kotlinx.serialization.Serializable

@Serializable
enum class Mood(val displayName: String) {
    LONELY("Lonely"),
    SAD("Sad"),
    BORED("Bored"),
    RESTLESS("Restless"),
    STRESSED("Stressed"),
    TIRED("Tired"),
    UNMOTIVATED("Unmotivated"),
    JUST_WANT_SOMETHING_TO_DO("Just want something to do")
}

@Serializable
enum class EnergyLevel(val displayName: String) {
    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High")
}

@Serializable
enum class SocialPreference(val displayName: String) {
    ALONE("Alone"),
    EITHER("Either"),
    AROUND_PEOPLE("Around people")
}

@Serializable
enum class TimeAvailable(val minutes: Int, val displayName: String) {
    TEN_MINUTES(10, "10 minutes"),
    THIRTY_MINUTES(30, "30 minutes"),
    ONE_HOUR(60, "1 hour"),
    MORE_THAN_ONE_HOUR(120, "More than 1 hour")
}

// New Static Profile Enums

enum class LivingSituation(val displayName: String) {
    ALONE("I live alone"),
    PARTNER("Partner or spouse"),
    FAMILY("Family"),
    ROOMMATES("Roommates"),
    OTHER("Other")
}

enum class ExerciseFrequency(val displayName: String) {
    DAILY("Daily"),
    WEEKLY_FEW("A few times a week"),
    WEEKLY_ONCE("Once a week"),
    RARELY("Rarely"),
    NEVER("Never")
}

enum class EmploymentStatus(val displayName: String) {
    FULL_TIME("Working full-time"),
    PART_TIME("Working part-time"),
    STUDENT("Student"),
    LOOKING("Looking for work"),
    RETIRED("Retired"),
    OTHER("Other")
}

enum class BudgetPreference(val displayName: String) {
    FREE("Free only"),
    UNDER_10("Under $10"),
    UNDER_25("Under $25"),
    NO_PREFERENCE("No preference")
}

enum class Interest(val displayName: String) {
    WALKING("Walking"),
    HIKING("Hiking"),
    EXERCISE("Exercise"),
    READING("Reading"),
    MOVIES_TV("Movies & TV"),
    GAMING("Gaming"),
    MUSIC("Music"),
    COOKING("Cooking"),
    ARTS_CRAFTS("Arts & crafts"),
    LEARNING("Learning"),
    EXPLORING("Exploring new places"),
    VOLUNTEERING("Volunteering"),
    MEETING_PEOPLE("Meeting new people"),
    COFFEE_SHOPS("Coffee shops"),
    MUSEUMS("Museums"),
    NATURE("Nature")
}

enum class Obstacle(val displayName: String) {
    LOW_ENERGY("Low energy"),
    ANXIETY("Anxiety"),
    MOTIVATION("Lack of motivation"),
    DECISION_PARALYSIS("I don't know what to do"),
    MONEY("Money"),
    TIME("Time"),
    TRANSPORTATION("Transportation"),
    WEATHER("Bad weather"),
    PREFER_HOME("I prefer staying home")
}

enum class GoalFocus(val displayName: String) {
    FIND_DO("Find something to do"),
    LESS_LONELY("Feel less lonely"),
    MEET_PEOPLE("Meet new people"),
    HOBBIES("Discover new hobbies"),
    PRODUCTIVE("Be more productive"),
    LESS_HOME("Spend less time at home"),
    HEALTHY_HABITS("Build healthier habits"),
    RELAX("Relax and unwind")
}

enum class LocationType(val displayName: String) {
    HOME("Home"),
    OUTDOORS("Outdoors"),
    INDOORS_PUBLIC("Public indoors"),
    WATER("Near or in water"),
    ANYWHERE("Anywhere")
}

enum class WeatherPreference(val displayName: String) {
    ANY("Any weather"),
    SUNNY("Sunny"),
    RAINY("Rainy"),
    SNOW("Snow"),
    INDOOR_ONLY("Indoor only")
}

enum class ActivityIntensity(val displayName: String) {
    VERY_LIGHT("Very light"),
    LIGHT("Light"),
    MODERATE("Moderate"),
    HIGH("High")
}

enum class Equipment(val displayName: String) {
    BICYCLE("Bicycle"),
    PHONE("Phone"),
    COMPUTER("Computer"),
    SWIMWEAR("Swimwear"),
    HEADPHONES("Headphones"),
    CAMERA("Camera"),
    BOOK("Book"),
    SPORTS_CLOTHES("Sports clothes")
}

enum class ActivityTag(val displayName: String) {
    RELAXING("Relaxing"),
    PRODUCTIVE("Productive"),
    CREATIVE("Creative"),
    SOCIAL("Social"),
    OUTDOORS("Outdoors"),
    EXERCISE("Exercise"),
    LEARNING("Learning"),
    GAMING("Gaming"),
    MINDFULNESS("Mindfulness"),
    EXPLORATION("Exploration")
}

data class UserProfile(
    val livingSituation: LivingSituation = LivingSituation.OTHER,
    val socialEnjoyment: String = "Sometimes", // Yes, Sometimes, No
    val exerciseFrequency: ExerciseFrequency = ExerciseFrequency.RARELY,
    val employmentStatus: EmploymentStatus = EmploymentStatus.OTHER,
    val budgetPreference: BudgetPreference = BudgetPreference.NO_PREFERENCE,
    val interests: List<Interest> = emptyList(),
    val obstacles: List<Obstacle> = emptyList(),
    val focus: GoalFocus = GoalFocus.FIND_DO,
)

@Serializable
data class GoalActivity(
    val id: Int? = null,
    val name: String,
    val whyFits: String,
    val firstStep: String
)

@Serializable
data class UserCheckIn(
    val mood: Mood,
    val energyLevel: EnergyLevel,
    val socialPreference: SocialPreference,
    val timeAvailable: TimeAvailable
)

@Serializable
data class JournalEntry(
    val id: Int? = null,
    val customer_id: String? = null,
    val content: String,
    val entry_date: String? = null,
    val created_at: String? = null
)

@Serializable
data class UserStats(
    val customer_id: String,
    val total_points: Int = 0,
    val last_mission_reset: String? = null,
    val journal_mission_completed: Boolean = false,
    val goal_mission_completed: Boolean = false
)

@Serializable
data class Customer(
    val id: String,
    val name: String?,
    val email: String,
    val created_at: String? = null
)
