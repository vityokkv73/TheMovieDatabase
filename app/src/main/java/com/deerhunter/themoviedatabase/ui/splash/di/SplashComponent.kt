package com.deerhunter.themoviedatabase.ui.splash.di

import com.deerhunter.themoviedatabase.navigation.di.INavigationProvider
import com.deerhunter.themoviedatabase.navigation.di.NavigationComponent
import com.deerhunter.themoviedatabase.repository.configuration.di.IConfigurationRepositoryProvider
import com.deerhunter.themoviedatabase.ui.splash.SplashFragment
import dagger.Component

@Component(modules = [SplashModule::class], dependencies = [IConfigurationRepositoryProvider::class, INavigationProvider::class])
interface SplashComponent {
    fun inject(fragment: SplashFragment)
}