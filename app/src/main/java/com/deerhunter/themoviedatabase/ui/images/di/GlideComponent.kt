package com.deerhunter.themoviedatabase.ui.images.di

import com.deerhunter.themoviedatabase.application.di.IApplicationProvider
import com.deerhunter.themoviedatabase.prefs.di.IPreferencesProvider
import com.deerhunter.themoviedatabase.ui.images.GlideInitializer
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [IPreferencesProvider::class, IApplicationProvider::class]
)
interface GlideComponent {
    fun inject(glideInitializer: GlideInitializer)
}