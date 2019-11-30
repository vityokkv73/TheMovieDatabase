package com.deerhunter.themoviedatabase.ui.splash.di

import androidx.lifecycle.ViewModel
import com.deerhunter.themoviedatabase.ui.base.ViewModelKey
import com.deerhunter.themoviedatabase.ui.base.ViewModelModule
import com.deerhunter.themoviedatabase.ui.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SplashModule: ViewModelModule() {

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    internal abstract fun splashViewModel(viewModel: SplashViewModel): ViewModel
}