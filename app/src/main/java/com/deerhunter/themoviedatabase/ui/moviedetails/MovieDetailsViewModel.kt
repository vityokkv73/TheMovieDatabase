package com.deerhunter.themoviedatabase.ui.moviedetails

import androidx.lifecycle.*
import com.deerhunter.themoviedatabase.data.Movie
import com.deerhunter.themoviedatabase.repository.movies.IMoviesRepository
import javax.inject.Inject
import kotlin.properties.Delegates

class MovieDetailsViewModel @Inject constructor(
    private val moviesRepository: IMoviesRepository
) : ViewModel() {

    val movie: LiveData<MovieResult> = liveData {
        emit(MovieResult.Loading)
        try {
            emit(MovieResult.Content(moviesRepository.getMovieById(movieId)))
        } catch (ex: Exception) {
            emit(MovieResult.Error(ex))
        }
    }

    var movieId: Int by Delegates.notNull()

    sealed class MovieResult {
        object Loading : MovieResult()
        class Content(val movie: Movie) : MovieResult()
        class Error(val ex: Throwable) : MovieResult()
    }
}