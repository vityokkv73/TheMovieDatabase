package com.deerhunter.themoviedatabase.application.di

import android.app.ActivityManager
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val applicationContext: Context) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = applicationContext

    @Provides
    @Singleton
    fun provideActivityManager(context: Context): ActivityManager {
        return context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    }
}