package com.devcolibri.eldarovich99.advancedtodolist.db.converter

import android.arch.persistence.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.*

class DateConverter {
    companion object {
        @TypeConverter
        @JvmStatic
        fun fromDate(date: Date): String{
            return SimpleDateFormat("yyyy-MM-dd HH:mm:SS", Locale.getDefault()).format(date)
        }
        @TypeConverter
        @JvmStatic
        fun toDate(date: String): Date{
            return SimpleDateFormat("yyyy-MM-dd HH:mm:SS", Locale.getDefault()).parse(date)
        }

    }
}