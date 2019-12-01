package com.deerhunter.themoviedatabase.ui.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.deerhunter.themoviedatabase.R
import com.deerhunter.themoviedatabase.data.PopularMovieBrief
import com.deerhunter.themoviedatabase.ui.base.injectViewModel
import com.deerhunter.themoviedatabase.ui.extensions.withArguments
import com.deerhunter.themoviedatabase.ui.moviedetails.di.DaggerMovieDetailsComponent
import me.vponomarenko.injectionmanager.x.XInjectionManager
import timber.log.Timber
import javax.inject.Inject

class MovieDetailsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MovieDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = injectViewModel(viewModelFactory)
        viewModel.movieId = arguments!!.getInt(ID)
        viewModel.movie.observe(viewLifecycleOwner, Observer { movie ->
            showMovieDetails(movie)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerMovieDetailsComponent.builder()
            .iMoviesRepositoryProvider(XInjectionManager.findComponent())
            .build().inject(this)
    }

    private fun showMovieDetails(movie: MovieDetailsViewModel.MovieResult?) {
        Timber.d("movie = $movie")
    }

    companion object {
        private const val ID = "id"

        fun newInstance(popularMovie: PopularMovieBrief) =
            MovieDetailsFragment().withArguments(ID to popularMovie.id)
    }
}