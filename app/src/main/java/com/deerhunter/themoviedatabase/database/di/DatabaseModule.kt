package com.deerhunter.themoviedatabase.database.di

import android.content.Context
import androidx.room.Room
import com.deerhunter.themoviedatabase.database.TmdbDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideTmdbDatabase(context: Context): TmdbDatabase =
        Room.databaseBuilder(context, TmdbDatabase::class.java, "tmdb_database").build()
}