package com.deerhunter.themoviedatabase.prefs

import com.deerhunter.themoviedatabase.data.Configuration

interface IConfigurationPrefs {
    fun getConfiguration(): Configuration
    fun saveConfiguration(configuration: Configuration)
}