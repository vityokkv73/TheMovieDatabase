package com.deerhunter.themoviedatabase.network

import com.deerhunter.themoviedatabase.data.Configuration
import com.deerhunter.themoviedatabase.data.Movie
import com.deerhunter.themoviedatabase.data.MoviesList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("movie/top_rated")
    suspend fun getPopularMovies(@Query("page") page: Int): MoviesList

    @GET("configuration")
    suspend fun getConfiguration(): Configuration

    @GET("movie/{movie_id}")
    suspend fun getMovieDetailsById(@Path("movie_id") movieId: Int): Movie
}