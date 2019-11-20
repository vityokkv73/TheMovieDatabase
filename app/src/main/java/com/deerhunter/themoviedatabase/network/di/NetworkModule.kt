package com.deerhunter.themoviedatabase.network.di

import com.deerhunter.themoviedatabase.network.Api
import com.deerhunter.themoviedatabase.network.AuthorizationInterceptor
import com.deerhunter.themoviedatabase.network.IAuthorizationInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    internal fun provideAuthorizationInterceptor(): IAuthorizationInterceptor {
        return AuthorizationInterceptor()
    }

    @Provides
    @Singleton
    internal fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(
        httpInterceptor: HttpLoggingInterceptor,
        authorizationInterceptor: IAuthorizationInterceptor
    ): OkHttpClient {
        return OkHttpClient().newBuilder()
            .connectTimeout(60 * 1000, TimeUnit.MILLISECONDS)
            .readTimeout(60 * 1000, TimeUnit.MILLISECONDS)
            .addInterceptor(httpInterceptor)
            .addInterceptor(authorizationInterceptor)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }
}