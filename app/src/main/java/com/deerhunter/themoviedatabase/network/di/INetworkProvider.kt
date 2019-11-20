package com.deerhunter.themoviedatabase.network.di

import com.deerhunter.themoviedatabase.network.Api

interface INetworkProvider {
    fun provideApi(): Api
}
