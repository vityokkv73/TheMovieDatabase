package com.deerhunter.themoviedatabase.prefs.di

import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [PreferencesModule::class]
)
@Singleton
interface PreferencesComponent : IPreferencesProvider