package com.deerhunter.themoviedatabase.repository.di

import com.deerhunter.themoviedatabase.repository.IMoviesRepository

interface IMoviesRepositoryProvider {
    fun provideMoviesRepository(): IMoviesRepository
}