package com.deerhunter.themoviedatabase.database.di

import com.deerhunter.themoviedatabase.database.TmdbDatabase

interface IDatabaseProvider {
    fun provideTmdbDatabase(): TmdbDatabase
}
