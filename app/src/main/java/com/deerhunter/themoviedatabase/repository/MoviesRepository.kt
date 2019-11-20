package com.deerhunter.themoviedatabase.repository

import com.deerhunter.themoviedatabase.data.MovieBrief
import com.deerhunter.themoviedatabase.network.Api
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val api: Api): IMoviesRepository {
    override suspend fun getPopularMovies(page: Int): List<MovieBrief> {
        return api.getPopularMovies(page).results
    }
}