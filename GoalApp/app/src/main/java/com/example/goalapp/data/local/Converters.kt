package com.example.goalapp.data.local

// import androidx.room.TypeConverter
import com.example.goalapp.data.Frequency
import com.example.goalapp.data.Mood
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {
    // @TypeConverter
    fun fromMood(value: Mood?): String? = value?.name

    // @TypeConverter
    fun toMood(value: String?): Mood? = value?.let { Mood.valueOf(it) }

    // @TypeConverter
    fun fromFrequency(value: Frequency?): String? = value?.name

    // @TypeConverter
    fun toFrequency(value: String?): Frequency? = value?.let { Frequency.valueOf(it) }

    // @TypeConverter
    fun fromStringList(value: List<String>): String = Json.encodeToString(value)

    // @TypeConverter
    fun toStringList(value: String): List<String> = Json.decodeFromString(value)
}
