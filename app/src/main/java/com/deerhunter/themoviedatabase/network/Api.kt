package com.deerhunter.themoviedatabase.network

import com.deerhunter.themoviedatabase.data.MovieBrief
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("movie/top_rated")
    suspend fun getPopularMovies(@Query("page") page: Int): List<MovieBrief>
}