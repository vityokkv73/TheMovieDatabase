package com.deerhunter.themoviedatabase.ui.movieslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deerhunter.themoviedatabase.repository.IMoviesRepository
import com.deerhunter.themoviedatabase.ui.base.delegates.MovieBriefUiItem
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesListViewModel @Inject constructor(private val moviesRepository: IMoviesRepository): ViewModel() {
    private val moviesLiveDataInt = MutableLiveData<List<MovieBriefUiItem>>()
    val moviesLiveData: LiveData<List<MovieBriefUiItem>> = moviesLiveDataInt

    init {
        viewModelScope.launch {
            moviesLiveDataInt.value = arrayListOf()
            val topRatedMovies = moviesRepository.getPopularMovies(1)
            moviesLiveDataInt.value = topRatedMovies.map { MovieBriefUiItem(it) }
        }
    }
}
