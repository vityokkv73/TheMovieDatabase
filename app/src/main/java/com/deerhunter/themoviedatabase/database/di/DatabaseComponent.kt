package com.deerhunter.themoviedatabase.database.di

import com.deerhunter.themoviedatabase.application.di.IApplicationProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DatabaseModule::class], dependencies = [IApplicationProvider::class])
interface DatabaseComponent : IDatabaseProvider