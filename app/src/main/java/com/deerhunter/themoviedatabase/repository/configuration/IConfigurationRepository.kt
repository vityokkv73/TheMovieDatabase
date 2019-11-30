package com.deerhunter.themoviedatabase.repository.configuration

import com.deerhunter.themoviedatabase.data.Configuration

interface IConfigurationRepository {
    suspend fun getConfiguration(): Configuration
    suspend fun updateConfiguration()
}