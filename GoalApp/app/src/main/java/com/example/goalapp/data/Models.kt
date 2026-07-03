package com.example.goalapp.data

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

enum class EnergyLevel(val displayName: String) {
    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High")
}

enum class SocialPreference(val displayName: String) {
    ALONE("Alone"),
    EITHER("Either"),
    AROUND_PEOPLE("Around people")
}

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

data class UserProfile(
    val livingSituation: LivingSituation = LivingSituation.OTHER,
    val socialEnjoyment: String = "Sometimes", // Yes, Sometimes, No
    val exerciseFrequency: ExerciseFrequency = ExerciseFrequency.RARELY,
    val employmentStatus: EmploymentStatus = EmploymentStatus.OTHER,
    val budgetPreference: BudgetPreference = BudgetPreference.NO_PREFERENCE,
    val interests: List<Interest> = emptyList(),
    val obstacles: List<Obstacle> = emptyList(),
    val focus: GoalFocus = GoalFocus.FIND_DO
)

data class GoalActivity(
    val name: String,
    val whyFits: String,
    val firstStep: String,
    val moods: List<Mood>,
    val minEnergy: EnergyLevel,
    val socialPreference: SocialPreference,
    val maxTimeMinutes: Int,
    val interests: List<Interest> = emptyList(),
    val isFree: Boolean = true
)

data class UserCheckIn(
    val mood: Mood,
    val energyLevel: EnergyLevel,
    val socialPreference: SocialPreference,
    val timeAvailable: TimeAvailable
)
