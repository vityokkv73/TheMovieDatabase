package com.deerhunter.themoviedatabase

import android.app.Application
import timber.log.Timber

class TmdbApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}