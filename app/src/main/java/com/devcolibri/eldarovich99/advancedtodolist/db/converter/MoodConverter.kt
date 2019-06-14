package com.devcolibri.eldarovich99.advancedtodolist.db.converter

import androidx.room.TypeConverter
import com.devcolibri.eldarovich99.advancedtodolist.utils.Mood
import kotlin.math.absoluteValue

class MoodConverter {
    companion object{
        @TypeConverter
        @JvmStatic
        fun fromMood(mood: Mood): Int{
            return mood.ordinal.absoluteValue
        }
        @TypeConverter
        @JvmStatic
        fun toMood(index: Int): Mood {
            return Mood.values()[index]
        }
    }
}