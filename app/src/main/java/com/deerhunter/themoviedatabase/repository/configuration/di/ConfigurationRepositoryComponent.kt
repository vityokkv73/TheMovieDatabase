package com.deerhunter.themoviedatabase.repository.configuration.di

import com.deerhunter.themoviedatabase.network.di.INetworkProvider
import com.deerhunter.themoviedatabase.prefs.di.IPreferencesProvider
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [ConfigurationRepositoryModule::class],
    dependencies = [INetworkProvider::class, IPreferencesProvider::class]
)
@Singleton
interface ConfigurationRepositoryComponent : IConfigurationRepositoryProvider