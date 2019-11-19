package com.deerhunter.themoviedatabase.repository

import com.deerhunter.themoviedatabase.data.MovieBrief

interface IMoviesRepository {
    suspend fun getPopularMovies(page: Int): List<MovieBrief>
}