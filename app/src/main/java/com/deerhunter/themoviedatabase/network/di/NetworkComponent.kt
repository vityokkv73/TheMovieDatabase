package com.deerhunter.themoviedatabase.network.di

import com.deerhunter.themoviedatabase.application.di.IApplicationProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class], dependencies = [IApplicationProvider::class])
interface NetworkComponent: INetworkProvider