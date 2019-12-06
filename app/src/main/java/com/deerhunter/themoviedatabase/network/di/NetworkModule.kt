package com.deerhunter.themoviedatabase.network.di

import android.content.Context
import com.deerhunter.themoviedatabase.network.Api
import com.deerhunter.themoviedatabase.network.AuthorizationInterceptor
import com.deerhunter.themoviedatabase.network.IAuthorizationInterceptor
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import java.text.DateFormat
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
    internal fun provideCache(context: Context): Cache {
        return Cache(context.cacheDir, 10 * 1024 * 1024L)
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(
        httpInterceptor: HttpLoggingInterceptor,
        authorizationInterceptor: IAuthorizationInterceptor,
        cache: Cache
    ): OkHttpClient {
        return OkHttpClient().newBuilder()
            .connectTimeout(60 * 1000, TimeUnit.MILLISECONDS)
            .readTimeout(60 * 1000, TimeUnit.MILLISECONDS)
            .cache(cache)
            .addInterceptor(httpInterceptor)
            .addInterceptor(authorizationInterceptor)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        return GsonBuilder().apply {
            setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            setDateFormat("yyyy-MM-dd")
        }.create()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }
}