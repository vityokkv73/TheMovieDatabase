package com.deerhunter.themoviedatabase

import android.app.Application
import com.deerhunter.themoviedatabase.network.di.DaggerNetworkComponent
import com.deerhunter.themoviedatabase.network.di.NetworkComponent
import com.deerhunter.themoviedatabase.network.di.NetworkModule
import com.deerhunter.themoviedatabase.repository.di.DaggerMoviesRepositoryComponent
import com.deerhunter.themoviedatabase.repository.di.MoviesRepositoryComponent
import com.deerhunter.themoviedatabase.repository.di.MoviesRepositoryModule
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
        XInjectionManager.instance.bindComponent(object : IHasComponent<NetworkComponent> {
            override fun getComponent() =
                DaggerNetworkComponent.builder().networkModule(NetworkModule()).build()
        })
        XInjectionManager.instance.bindComponent(object : IHasComponent<MoviesRepositoryComponent> {
            override fun getComponent(): MoviesRepositoryComponent =
                DaggerMoviesRepositoryComponent.builder().moviesRepositoryModule(
                    MoviesRepositoryModule()
                ).iNetworkProvider(XInjectionManager.findComponent()).build()
        })
    }
}