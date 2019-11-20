package com.deerhunter.themoviedatabase.repository.di

import com.deerhunter.themoviedatabase.network.di.INetworkProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MoviesRepositoryModule::class], dependencies = [INetworkProvider::class])
interface MoviesRepositoryComponent: IMoviesRepositoryProvider