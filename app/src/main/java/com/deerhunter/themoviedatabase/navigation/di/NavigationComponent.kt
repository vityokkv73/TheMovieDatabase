package com.deerhunter.themoviedatabase.navigation.di

import com.deerhunter.themoviedatabase.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NavigationModule::class])
interface NavigationComponent {
    fun inject(activity: MainActivity)
}