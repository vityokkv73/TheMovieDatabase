package com.deerhunter.themoviedatabase.repository.configuration

import com.deerhunter.themoviedatabase.data.Configuration
import com.deerhunter.themoviedatabase.network.Api
import com.deerhunter.themoviedatabase.prefs.IConfigurationPrefs

class ConfigurationRepository(
    private val api: Api,
    private val configurationPrefs: IConfigurationPrefs
) : IConfigurationRepository {
    override suspend fun getConfiguration(): Configuration {
        return api.getConfiguration()
    }

    override suspend fun updateConfiguration() {
        val configuration = getConfiguration()
        configurationPrefs.saveConfiguration(configuration)
    }
}