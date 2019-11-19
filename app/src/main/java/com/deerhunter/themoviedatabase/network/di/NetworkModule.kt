package com.deerhunter.themoviedatabase.network.di

import com.deerhunter.themoviedatabase.network.Api
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
@Module
class NetworkModule {

    @Provides
    fun provideRetrofit(): Retrofit {
return Builder()
        .baseUrl("https://api.github.com/")
        .build()
}

    @Provides
    fun provideApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }
}