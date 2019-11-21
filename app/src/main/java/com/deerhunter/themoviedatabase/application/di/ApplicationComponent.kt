package com.deerhunter.themoviedatabase.application.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent : IApplicationProvider