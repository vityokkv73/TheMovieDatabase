package com.deerhunter.themoviedatabase.repository.configuration.di

import com.deerhunter.themoviedatabase.network.Api
import com.deerhunter.themoviedatabase.prefs.IConfigurationPrefs
import com.deerhunter.themoviedatabase.repository.configuration.ConfigurationRepository
import com.deerhunter.themoviedatabase.repository.configuration.IConfigurationRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ConfigurationRepositoryModule {

    @Singleton
    @Provides
    fun provideConfigurationRepository(api: Api, configurationPrefs: IConfigurationPrefs) : IConfigurationRepository {
        return ConfigurationRepository(api, configurationPrefs)
    }
}