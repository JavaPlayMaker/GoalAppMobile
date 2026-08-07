package com.example.goalapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
enum class Mood(val displayName: String) {
    LONELY("Lonely"), SAD("Sad"), BORED("Bored"),
    RESTLESS("Restless"), STRESSED("Stressed"), TIRED("Tired"),
    UNMOTIVATED("Unmotivated"), JUST_WANT_SOMETHING_TO_DO("Just want something to do")
}

@Serializable
enum class EnergyLevel(val displayName: String) {
    LOW("Low"), MEDIUM("Medium"), HIGH("High")
}

@Serializable
enum class SocialPreference(val displayName: String) {
    ALONE("Alone"), EITHER("Either"), AROUND_PEOPLE("Around people")
}

@Serializable
enum class TimeAvailable(val minutes: Int, val displayName: String) {
    TEN_MINUTES(10, "10 minutes"),
    THIRTY_MINUTES(30, "30 minutes"),
    ONE_HOUR(60, "1 hour"),
    MORE_THAN_ONE_HOUR(120, "More than 1 hour")
}

@Serializable
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

@Serializable
enum class LivingSituation(val displayName: String) {
    ALONE("I live alone"), PARTNER("Partner or spouse"),
    FAMILY("Family"), ROOMMATES("Roommates"), OTHER("Other")
}

@Serializable
enum class ExerciseFrequency(val displayName: String) {
    DAILY("Daily"), WEEKLY_FEW("A few times a week"),
    WEEKLY_ONCE("Once a week"), RARELY("Rarely"), NEVER("Never")
}

@Serializable
enum class EmploymentStatus(val displayName: String) {
    FULL_TIME("Working full-time"), PART_TIME("Working part-time"),
    STUDENT("Student"), LOOKING("Looking for work"),
    RETIRED("Retired"), OTHER("Other")
}

@Serializable
enum class BudgetPreference(val displayName: String) {
    FREE("Free only"), UNDER_10("Under $10"),
    UNDER_25("Under $25"), NO_PREFERENCE("No preference")
}

@Serializable
enum class Interest(val displayName: String) {
    WALKING("Walking"), HIKING("Hiking"), EXERCISE("Exercise"),
    READING("Reading"), MOVIES_TV("Movies & TV"), GAMING("Gaming"),
    MUSIC("Music"), COOKING("Cooking"), ARTS_CRAFTS("Arts & crafts"),
    LEARNING("Learning"), EXPLORING("Exploring new places"),
    VOLUNTEERING("Volunteering"), MEETING_PEOPLE("Meeting new people"),
    COFFEE_SHOPS("Coffee shops"), MUSEUMS("Museums"), NATURE("Nature")
}

@Serializable
enum class Obstacle(val displayName: String) {
    LOW_ENERGY("Low energy"), ANXIETY("Anxiety"),
    MOTIVATION("Lack of motivation"), DECISION_PARALYSIS("I don't know what to do"),
    MONEY("Money"), TIME("Time"), TRANSPORTATION("Transportation"),
    WEATHER("Bad weather"), PREFER_HOME("I prefer staying home")
}

@Serializable
data class UserProfile(
    val livingSituation: LivingSituation = LivingSituation.OTHER,
    val socialEnjoyment: String = "Sometimes",
    val exerciseFrequency: ExerciseFrequency = ExerciseFrequency.RARELY,
    val employmentStatus: EmploymentStatus = EmploymentStatus.OTHER,
    val budgetPreference: BudgetPreference = BudgetPreference.NO_PREFERENCE,
    val interests: List<Interest> = emptyList(),
    val obstacles: List<Obstacle> = emptyList(),
    val focus: GoalFocus = GoalFocus.FIND_DO,
)

@Serializable
enum class Frequency {
    DAILY, WEEKLY, MONTHLY
}

@Serializable
data class Customer(
    val id: String,
    val name: String?,
    val email: String,
    val created_at: String? = null
)

// --- Room Entities ---

@Entity(tableName = "journal_entries")
@Serializable
data class JournalEntry(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val customer_id: String? = null, // Added back for compatibility
    val content: String,
    val entry_date: String? = null, // Added back for compatibility
    val photoUri: String? = null,
    val moodBefore: Mood? = null,
    val moodAfter: Mood? = null,
    val linkedActivityId: Int? = null,
    val templateType: String? = null,
    val created_at: Long = System.currentTimeMillis()
)

@Entity(tableName = "user_stats")
@Serializable
data class UserStats(
    @PrimaryKey val id: String = "local_user",
    val customer_id: String? = null, // Added back for compatibility
    val total_points: Int = 0,
    val level: Int = 1,
    val last_mission_reset: String? = null, // Added back for compatibility
    val journal_mission_completed: Boolean = false,
    val goal_mission_completed: Boolean = false,
    val unlocked_games: Boolean = false,
    val unlocked_learn: Boolean = false
)

@Entity(tableName = "goals")
@Serializable
data class Goal(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val name: String,
    val currentValue: Float = 0f,
    val targetValue: Float,
    val unit: String? = null,
    val pointsValue: Int = 50,
    val isCompleted: Boolean = false,
    val category: String = "General"
)

@Entity(tableName = "activity_logs")
@Serializable
data class ActivityLog(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val name: String,
    val category: String,
    val startTime: Long,
    val endTime: Long? = null,
    val moodBefore: Mood? = null,
    val moodAfter: Mood? = null,
    val pointsEarned: Int = 0
)

@Entity(tableName = "habits")
@Serializable
data class Habit(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val name: String,
    val frequency: Frequency,
    val icon: String? = null,
    val color: Int? = null
)

@Entity(tableName = "habit_logs")
@Serializable
data class HabitLog(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val habitId: Int,
    val timestamp: Long = System.currentTimeMillis()
)

// Legacy / UI Models
@Serializable
data class GoalActivity(
    val id: Int? = null,
    val name: String,
    val whyFits: String,
    val firstStep: String,
    val category: String = "General"
)

@Serializable
data class UserCheckIn(
    val mood: Mood,
    val energyLevel: EnergyLevel,
    val socialPreference: SocialPreference,
    val timeAvailable: TimeAvailable
)
