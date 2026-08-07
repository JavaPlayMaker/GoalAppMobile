package com.example.goalapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.goalapp.data.*

@Database(
    entities = [
        JournalEntry::class,
        UserStats::class,
        Goal::class,
        ActivityLog::class,
        Habit::class,
        HabitLog::class
    ],
    version = 2, // Incrementing version because we added several fields
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun goalDao(): GoalDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "goal_database"
                )
                .fallbackToDestructiveMigration() // Simple strategy for development
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
