package com.deerhunter.themoviedatabase.application

import android.app.Application
import com.deerhunter.themoviedatabase.application.di.ApplicationComponent
import com.deerhunter.themoviedatabase.application.di.ApplicationModule
import com.deerhunter.themoviedatabase.application.di.DaggerApplicationComponent
import com.deerhunter.themoviedatabase.database.TmdbDatabase
import com.deerhunter.themoviedatabase.database.di.DaggerDatabaseComponent
import com.deerhunter.themoviedatabase.database.di.DatabaseComponent
import com.deerhunter.themoviedatabase.network.di.DaggerNetworkComponent
import com.deerhunter.themoviedatabase.network.di.NetworkComponent
import com.deerhunter.themoviedatabase.repository.di.DaggerMoviesRepositoryComponent
import com.deerhunter.themoviedatabase.repository.di.MoviesRepositoryComponent
import me.vponomarenko.injectionmanager.IHasComponent
import me.vponomarenko.injectionmanager.x.XInjectionManager
import timber.log.Timber
import javax.inject.Inject

class TmdbApplication : Application() {
    @Inject
    lateinit var database: TmdbDatabase

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
            override fun getComponent() = DaggerNetworkComponent.create()
        })
        XInjectionManager.bindComponent(object : IHasComponent<DatabaseComponent> {
            override fun getComponent() =
                DaggerDatabaseComponent.builder()
                    .iApplicationProvider(XInjectionManager.findComponent())
                    .build()
        })
        XInjectionManager.bindComponent(object : IHasComponent<MoviesRepositoryComponent> {
            override fun getComponent(): MoviesRepositoryComponent =
                DaggerMoviesRepositoryComponent.builder()
                    .iNetworkProvider(XInjectionManager.findComponent())
                    .iDatabaseProvider(XInjectionManager.findComponent())
                    .build()
        })
    }
}