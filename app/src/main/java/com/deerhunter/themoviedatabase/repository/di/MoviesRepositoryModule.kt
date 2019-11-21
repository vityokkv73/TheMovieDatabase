package com.deerhunter.themoviedatabase.repository.di

import com.deerhunter.themoviedatabase.database.TmdbDatabase
import com.deerhunter.themoviedatabase.network.Api
import com.deerhunter.themoviedatabase.repository.IMoviesRepository
import com.deerhunter.themoviedatabase.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MoviesRepositoryModule {

    @Provides
    @Singleton
    fun provideMoviesRepository(api: Api, database: TmdbDatabase): IMoviesRepository {
        return MoviesRepository(api, database)
    }
}