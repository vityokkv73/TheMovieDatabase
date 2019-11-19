package com.deerhunter.themoviedatabase.ui.movieslist.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.deerhunter.themoviedatabase.ui.base.ViewModelFactory
import com.deerhunter.themoviedatabase.ui.base.ViewModelKey
import com.deerhunter.themoviedatabase.ui.movieslist.MoviesListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MoviesListModule {

    @Binds
    internal abstract fun bindViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MoviesListViewModel::class)
    internal abstract fun moviesListViewModel(viewModel: MoviesListViewModel): ViewModel
}