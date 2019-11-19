package com.deerhunter.themoviedatabase.ui.movieslist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deerhunter.themoviedatabase.data.MovieBrief
import com.deerhunter.themoviedatabase.repository.IMoviesRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesListViewModel @Inject constructor(private val moviesRepository: IMoviesRepository): ViewModel() {
    val moviesLiveData = MutableLiveData<MutableList<MovieBrief>>()

    init {
        viewModelScope.launch {
            moviesLiveData.value = arrayListOf()
            val topRatedMovies = moviesRepository.getPopularMovies(0)
            moviesLiveData.value?.addAll(topRatedMovies)
        }
    }
}
