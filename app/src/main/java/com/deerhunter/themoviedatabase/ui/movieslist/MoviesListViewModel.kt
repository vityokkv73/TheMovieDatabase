package com.deerhunter.themoviedatabase.ui.movieslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.deerhunter.themoviedatabase.data.PopularMovieBrief
import com.deerhunter.themoviedatabase.navigation.screen.MovieDetailsScreen
import com.deerhunter.themoviedatabase.repository.movies.IMoviesRepository
import com.deerhunter.themoviedatabase.ui.base.delegates.MovieBriefUiItem
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MoviesListViewModel @Inject constructor(
    private val moviesRepository: IMoviesRepository,
    private val router: Router
) : ViewModel() {
    val moviesLiveData: LiveData<PagedList<MovieBriefUiItem>>

    init {
        val dataSourceFactory = moviesRepository.getPopularMoviesFromNetwork()
            .mapByPage { list -> list.map { MovieBriefUiItem(it) } }
        moviesLiveData = LivePagedListBuilder(dataSourceFactory, 40).build()
    }

    fun onMovieBriefClicked(movieBrief: PopularMovieBrief) {
        router.navigateTo(MovieDetailsScreen(movieBrief))
    }
}
