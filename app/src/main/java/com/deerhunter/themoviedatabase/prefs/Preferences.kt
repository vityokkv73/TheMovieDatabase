package com.deerhunter.themoviedatabase.prefs

import com.deerhunter.themoviedatabase.data.Configuration
import com.marcinmoskala.kotlinpreferences.PreferenceHolder

object Preferences: PreferenceHolder(), IConfigurationPrefs {
    private var configurationPref: Configuration? by bindToPreferenceFieldNullable()

    override fun getConfiguration(): Configuration {
        return configurationPref!!
    }

    override fun saveConfiguration(configuration: Configuration) {
        configurationPref = configuration
    }
}