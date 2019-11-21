package com.deerhunter.themoviedatabase.database

import androidx.room.TypeConverter
import java.util.*

class RoomConverters {
    @TypeConverter
    fun readListOfIntFromString(genreIds: String): List<Int> {
        return genreIds.split(",").mapNotNull { it.trim().toIntOrNull() }
    }

    @TypeConverter
    fun writeStringFromListOfInt(list: List<Int>): String {
        return list.joinToString(",")
    }

    @TypeConverter
    fun readDateFromLong(time: Long): Date {
        return Date(time)
    }

    @TypeConverter
    fun writeLongFromDate(date: Date): Long {
        return date.time
    }
}