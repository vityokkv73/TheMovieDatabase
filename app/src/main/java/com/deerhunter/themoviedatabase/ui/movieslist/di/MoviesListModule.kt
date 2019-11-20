package com.deerhunter.themoviedatabase.ui.movieslist.di

import androidx.lifecycle.ViewModel
import com.deerhunter.themoviedatabase.ui.base.ViewModelKey
import com.deerhunter.themoviedatabase.ui.base.ViewModelModule
import com.deerhunter.themoviedatabase.ui.movieslist.MoviesListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MoviesListModule: ViewModelModule() {

    @Binds
    @IntoMap
    @ViewModelKey(MoviesListViewModel::class)
    internal abstract fun moviesListViewModel(viewModel: MoviesListViewModel): ViewModel
}