package com.deerhunter.themoviedatabase.prefs.di

import com.deerhunter.themoviedatabase.prefs.IConfigurationPrefs

interface IPreferencesProvider {
    fun provideConfigurationPrefs(): IConfigurationPrefs
}