package com.deerhunter.themoviedatabase.prefs.di

import android.content.Context
import com.deerhunter.themoviedatabase.prefs.IConfigurationPrefs
import com.deerhunter.themoviedatabase.prefs.Preferences
import com.google.gson.Gson
import com.marcinmoskala.kotlinpreferences.PreferenceHolder
import com.marcinmoskala.kotlinpreferences.gson.GsonSerializer
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PreferencesModule(applicationContext: Context) {
    init {
        PreferenceHolder.setContext(applicationContext)
        PreferenceHolder.serializer = GsonSerializer(Gson())
    }

    @Singleton
    @Provides
    fun provideConfigurationPrefs() : IConfigurationPrefs {
        return Preferences
    }
}