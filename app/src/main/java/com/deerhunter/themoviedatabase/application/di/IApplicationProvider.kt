package com.deerhunter.themoviedatabase.application.di

import android.content.Context

interface IApplicationProvider {
    fun provideApplicationContext(): Context
}
