package com.deerhunter.themoviedatabase.ui.movieslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.deerhunter.themoviedatabase.repository.movies.IMoviesRepository
import com.deerhunter.themoviedatabase.ui.base.delegates.MovieBriefUiItem
import javax.inject.Inject

class MoviesListViewModel @Inject constructor(private val moviesRepository: IMoviesRepository): ViewModel() {
    val moviesLiveData: LiveData<PagedList<MovieBriefUiItem>>

    init {
        val dataSourceFactory = moviesRepository.getPopularMoviesFromNetwork().mapByPage{ list -> list.map { MovieBriefUiItem(it) }}
        moviesLiveData = LivePagedListBuilder(dataSourceFactory, 40).build()
    }
}
