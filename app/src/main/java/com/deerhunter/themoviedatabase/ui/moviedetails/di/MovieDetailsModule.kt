package com.deerhunter.themoviedatabase.ui.moviedetails.di

import androidx.lifecycle.ViewModel
import com.deerhunter.themoviedatabase.ui.base.ViewModelKey
import com.deerhunter.themoviedatabase.ui.base.ViewModelModule
import com.deerhunter.themoviedatabase.ui.moviedetails.MovieDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MovieDetailsModule: ViewModelModule() {

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailsViewModel::class)
    internal abstract fun movieDetailsViewModel(viewModel: MovieDetailsViewModel): ViewModel
}