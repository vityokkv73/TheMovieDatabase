package com.deerhunter.themoviedatabase.network

import com.deerhunter.themoviedatabase.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor : IAuthorizationInterceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalHttpUrl = originalRequest.url()

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
            .build()

        val newRequest = originalRequest.newBuilder().url(url).build()

        return chain.proceed(newRequest)
    }
}

interface IAuthorizationInterceptor : Interceptor