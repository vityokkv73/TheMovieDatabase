package com.deerhunter.themoviedatabase.repository.movies

import androidx.paging.DataSource
import com.deerhunter.themoviedatabase.data.Movie
import com.deerhunter.themoviedatabase.data.PopularMovieBrief

interface IMoviesRepository {
    fun getPopularMoviesFromDb(): DataSource.Factory<Int, PopularMovieBrief>
    fun getPopularMoviesFromNetwork(): DataSource.Factory<Int, PopularMovieBrief>
    suspend fun getMovieById(movieId: Int): Movie?
}