package com.example.goalapp.data

import android.util.Log
import java.util.*
import java.util.concurrent.TimeUnit

data class MonthlyStats(
    val currentMonthProgress: Float, // 0.0 to 1.0
    val journalTotal: Int,
    val journalStreak: Int,
    val goalsTotal: Int,
    val goalRate: Float,
    val missionsTotal: Int,
    val missionStreak: Int,
    val insights: List<String>,
    val badges: List<Badge>
)

data class Badge(val title: String, val icon: String, val isEarned: Boolean)

class StatsEngine {

    fun processStats(records: List<ActivityRecord>): MonthlyStats {
        Log.d("StatsEngine", "Processing ${records.size} records")
        val now = Calendar.getInstance()
        val currentMonth = now.get(Calendar.MONTH)
        val currentYear = now.get(Calendar.YEAR)
        
        val currentRecords = records.filter { record ->
            val cal = Calendar.getInstance().apply { timeInMillis = record.timestamp }
            val recordMonth = cal.get(Calendar.MONTH)
            val recordYear = cal.get(Calendar.YEAR)
            val isSame = recordMonth == currentMonth && recordYear == currentYear
            Log.d("StatsEngine", "Record at ${Date(record.timestamp)} (Month: $recordMonth, Year: $recordYear) vs Current ($currentMonth, $currentYear) -> isSame: $isSame")
            isSame
        }

        // Journal Stats
        val journalCurrent = currentRecords.count { it.type == ActivityType.JOURNAL }
        val journalStreak = calculateStreak(records.filter { it.type == ActivityType.JOURNAL })

        // Goal Stats
        val goalsCurrent = currentRecords.count { it.type == ActivityType.GOAL }
        val goalRate = if (currentRecords.isEmpty()) 0f else goalsCurrent.toFloat() / currentRecords.size // Simplified

        // Mission Stats
        val missionsCurrent = currentRecords.count { it.type == ActivityType.MISSION_COMPLETE }
        val missionStreak = calculateStreak(records.filter { it.type == ActivityType.MISSION_COMPLETE })

        // Progress
        val daysInMonth = now.getActualMaximum(Calendar.DAY_OF_MONTH)
        val expectedTasks = daysInMonth * 2 // 2 tasks per day
        val progressCurrent = (missionsCurrent * 2 + (journalCurrent + goalsCurrent - missionsCurrent * 2)).toFloat() / expectedTasks

        // Insights
        val insights = mutableListOf<String>()
        if (journalStreak >= 7) insights.add("You've maintained a $journalStreak-day journaling streak.")
        if (missionsCurrent > 0) insights.add("You've completed $missionsCurrent missions this month!")
        if (goalsCurrent > 5) insights.add("You're doing great with your goals!")

        // Badges
        val badges = mutableListOf<Badge>()
        badges.add(Badge("7-Day Journal", "📝", journalStreak >= 7))
        badges.add(Badge("10 Goals", "🎯", goalsCurrent >= 10))
        badges.add(Badge("5-Day Mission", "🚀", missionStreak >= 5))

        val stats = MonthlyStats(
            currentMonthProgress = progressCurrent.coerceIn(0f, 1f),
            journalTotal = journalCurrent,
            journalStreak = journalStreak,
            goalsTotal = goalsCurrent,
            goalRate = goalRate,
            missionsTotal = missionsCurrent,
            missionStreak = missionStreak,
            insights = insights.take(3),
            badges = badges
        )
        Log.d("StatsEngine", "Processed Stats: $stats")
        return stats
    }

    private fun isSameMonth(timestamp: Long, month: Int, year: Int): Boolean {
        val cal = Calendar.getInstance().apply { timeInMillis = timestamp }
        return cal.get(Calendar.MONTH) == month && cal.get(Calendar.YEAR) == year
    }

    private fun calculateStreak(records: List<ActivityRecord>): Int {
        if (records.isEmpty()) return 0
        val sorted = records.sortedByDescending { it.timestamp }
        var streak = 0
        val today = Calendar.getInstance().apply { 
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        
        val checkDate = today.clone() as Calendar
        
        // Find if last record was today or yesterday
        val lastRecordDay = Calendar.getInstance().apply { 
            timeInMillis = sorted[0].timestamp
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        if (lastRecordDay.timeInMillis < today.timeInMillis - TimeUnit.DAYS.toMillis(1)) {
            Log.d("StatsEngine", "Streak broken: last record was at ${Date(sorted[0].timestamp)}")
            return 0
        }

        val uniqueDays = sorted.map { 
            Calendar.getInstance().apply { 
                timeInMillis = it.timestamp
                set(Calendar.HOUR_OF_DAY, 0)
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }.timeInMillis
        }.distinct()

        for (dayMillis in uniqueDays) {
            if (dayMillis == checkDate.timeInMillis) {
                streak++
                checkDate.add(Calendar.DATE, -1)
            } else if (dayMillis > checkDate.timeInMillis) {
                // Skip future dates if any
                continue
            } else {
                break
            }
        }
        return streak
    }
}
