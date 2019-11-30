package com.deerhunter.themoviedatabase.navigation.di

import com.deerhunter.themoviedatabase.ui.MainActivity
import com.deerhunter.themoviedatabase.ui.splash.SplashFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NavigationModule::class])
interface NavigationComponent: INavigationProvider {
    fun inject(activity: MainActivity)
}