package com.deerhunter.themoviedatabase.ui.moviedetails.di

import com.deerhunter.themoviedatabase.repository.movies.di.IMoviesRepositoryProvider
import com.deerhunter.themoviedatabase.ui.moviedetails.MovieDetailsFragment
import dagger.Component

@Component(modules = [MovieDetailsModule::class], dependencies = [IMoviesRepositoryProvider::class])
interface MovieDetailsComponent {
    fun inject(fragment: MovieDetailsFragment)
}