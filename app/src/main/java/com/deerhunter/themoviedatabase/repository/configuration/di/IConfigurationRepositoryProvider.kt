package com.deerhunter.themoviedatabase.repository.configuration.di

import com.deerhunter.themoviedatabase.repository.configuration.IConfigurationRepository

interface IConfigurationRepositoryProvider {
    fun provideIConfigurationRepository(): IConfigurationRepository
}