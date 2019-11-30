package com.deerhunter.themoviedatabase.repository.movies

import androidx.paging.DataSource
import com.deerhunter.themoviedatabase.data.PopularMovieBrief

interface IMoviesRepository {
    fun getPopularMovies(): DataSource.Factory<Int, PopularMovieBrief>
    fun getPopularMoviesFromNetwork(): DataSource.Factory<Int, PopularMovieBrief>
}