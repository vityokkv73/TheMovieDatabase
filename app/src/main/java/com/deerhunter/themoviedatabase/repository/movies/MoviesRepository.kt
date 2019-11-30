package com.deerhunter.themoviedatabase.repository.movies

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.deerhunter.themoviedatabase.data.PopularMovieBrief
import com.deerhunter.themoviedatabase.database.TmdbDatabase
import com.deerhunter.themoviedatabase.network.Api
import com.deerhunter.themoviedatabase.repository.movies.IMoviesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val api: Api,
    private val database: TmdbDatabase
) : IMoviesRepository, CoroutineScope {
    override val coroutineContext = GlobalScope.coroutineContext

    override fun getPopularMovies(): DataSource.Factory<Int, PopularMovieBrief> {
        return database.popularMovieBriefDao().loadAll()
    }

    override fun getPopularMoviesFromNetwork(): DataSource.Factory<Int, PopularMovieBrief> {
        return object : DataSource.Factory<Int, PopularMovieBrief>() {
            override fun create(): DataSource<Int, PopularMovieBrief> {
                return object : PageKeyedDataSource<Int, PopularMovieBrief>() {
                    override fun loadInitial(
                        params: LoadInitialParams<Int>,
                        callback: LoadInitialCallback<Int, PopularMovieBrief>
                    ) {
                        launch {
                            val moviesListResponse = api.getPopularMovies(1)
                            callback.onResult(
                                moviesListResponse.results,
                                null,
                                moviesListResponse.page + 1
                            )
                        }
                    }

                    override fun loadAfter(
                        params: LoadParams<Int>,
                        callback: LoadCallback<Int, PopularMovieBrief>
                    ) {
                        launch {
                            callback.onResult(
                                api.getPopularMovies(params.key).results,
                                params.key + 1
                            )
                        }
                    }

                    override fun loadBefore(
                        params: LoadParams<Int>,
                        callback: LoadCallback<Int, PopularMovieBrief>
                    ) {
                        launch {
                            callback.onResult(
                                api.getPopularMovies(params.key).results,
                                params.key - 1
                            )
                        }
                    }
                }
            }
        }
    }
}