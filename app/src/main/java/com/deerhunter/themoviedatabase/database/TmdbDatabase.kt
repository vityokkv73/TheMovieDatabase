package com.deerhunter.themoviedatabase.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.deerhunter.themoviedatabase.data.Movie
import com.deerhunter.themoviedatabase.data.PopularMovieBrief

@Database(entities = [PopularMovieBrief::class, Movie::class], version = 1)
@TypeConverters(RoomConverters::class)
abstract class TmdbDatabase : RoomDatabase() {
    abstract fun popularMovieBriefDao(): PopularMovieBriefDAO
    abstract fun movieDao(): MovieDAO
}