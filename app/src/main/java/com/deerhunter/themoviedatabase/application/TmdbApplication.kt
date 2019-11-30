package com.deerhunter.themoviedatabase.application

import android.app.Application
import com.deerhunter.themoviedatabase.application.di.ApplicationComponent
import com.deerhunter.themoviedatabase.application.di.ApplicationModule
import com.deerhunter.themoviedatabase.application.di.DaggerApplicationComponent
import com.deerhunter.themoviedatabase.database.di.DaggerDatabaseComponent
import com.deerhunter.themoviedatabase.database.di.DatabaseComponent
import com.deerhunter.themoviedatabase.network.di.DaggerNetworkComponent
import com.deerhunter.themoviedatabase.network.di.NetworkComponent
import com.deerhunter.themoviedatabase.prefs.di.DaggerPreferencesComponent
import com.deerhunter.themoviedatabase.prefs.di.PreferencesComponent
import com.deerhunter.themoviedatabase.prefs.di.PreferencesModule
import com.deerhunter.themoviedatabase.repository.configuration.di.ConfigurationRepositoryComponent
import com.deerhunter.themoviedatabase.repository.configuration.di.DaggerConfigurationRepositoryComponent
import com.deerhunter.themoviedatabase.repository.movies.di.DaggerMoviesRepositoryComponent
import com.deerhunter.themoviedatabase.repository.movies.di.MoviesRepositoryComponent
import com.deerhunter.themoviedatabase.ui.images.di.DaggerGlideComponent
import com.deerhunter.themoviedatabase.ui.images.di.GlideComponent
import me.vponomarenko.injectionmanager.IHasComponent
import me.vponomarenko.injectionmanager.x.XInjectionManager
import timber.log.Timber

class TmdbApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initDaggerComponents()
        Timber.plant(Timber.DebugTree())
    }

    private fun initDaggerComponents() {
        XInjectionManager.init(this)
        XInjectionManager.bindComponent(object : IHasComponent<ApplicationComponent> {
            override fun getComponent() =
                DaggerApplicationComponent.builder()
                    .applicationModule(ApplicationModule(this@TmdbApplication))
                    .build()
        })
        XInjectionManager.bindComponent(object : IHasComponent<NetworkComponent> {
            override fun getComponent() =
                DaggerNetworkComponent.builder()
                    .iApplicationProvider(XInjectionManager.findComponent())
                    .build()
        })
        XInjectionManager.bindComponent(object : IHasComponent<DatabaseComponent> {
            override fun getComponent() =
                DaggerDatabaseComponent.builder()
                    .iApplicationProvider(XInjectionManager.findComponent())
                    .build()
        })
        XInjectionManager.bindComponent(object : IHasComponent<PreferencesComponent> {
            override fun getComponent(): PreferencesComponent =
                DaggerPreferencesComponent.builder()
                    .preferencesModule(PreferencesModule(applicationContext))
                    .build()
        })
        XInjectionManager.bindComponent(object : IHasComponent<GlideComponent> {
            override fun getComponent(): GlideComponent =
                DaggerGlideComponent.builder()
                    .iPreferencesProvider(XInjectionManager.findComponent())
                    .iApplicationProvider(XInjectionManager.findComponent())
                    .build()
        })
        XInjectionManager.bindComponent(object : IHasComponent<ConfigurationRepositoryComponent> {
            override fun getComponent(): ConfigurationRepositoryComponent =
                DaggerConfigurationRepositoryComponent.builder()
                    .iNetworkProvider(XInjectionManager.findComponent())
                    .iPreferencesProvider(XInjectionManager.findComponent())
                    .build()
        })
        XInjectionManager.bindComponent(object :
            IHasComponent<MoviesRepositoryComponent> {
            override fun getComponent(): MoviesRepositoryComponent =
                DaggerMoviesRepositoryComponent.builder()
                    .iNetworkProvider(XInjectionManager.findComponent())
                    .iDatabaseProvider(XInjectionManager.findComponent())
                    .build()
        })
    }
}