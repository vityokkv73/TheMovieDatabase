package com.deerhunter.themoviedatabase.ui.movieslist.di

import com.deerhunter.themoviedatabase.navigation.di.INavigationProvider
import com.deerhunter.themoviedatabase.repository.movies.di.IMoviesRepositoryProvider
import com.deerhunter.themoviedatabase.ui.movieslist.MoviesListFragment
import dagger.Component

@Component(modules = [MoviesListModule::class], dependencies = [IMoviesRepositoryProvider::class, INavigationProvider::class])
interface MoviesListComponent {
    fun inject(fragment: MoviesListFragment)
}