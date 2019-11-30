package com.deerhunter.themoviedatabase.application.di

import android.app.ActivityManager
import android.content.Context

interface IApplicationProvider {
    fun provideApplicationContext(): Context
    fun provideActivityManager(): ActivityManager
}
