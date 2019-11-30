package com.deerhunter.themoviedatabase.repository.movies.di

import com.deerhunter.themoviedatabase.repository.movies.IMoviesRepository

interface IMoviesRepositoryProvider {
    fun provideMoviesRepository(): IMoviesRepository
}