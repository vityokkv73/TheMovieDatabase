package com.deerhunter.themoviedatabase.ui.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.deerhunter.themoviedatabase.R
import com.deerhunter.themoviedatabase.data.Movie
import com.deerhunter.themoviedatabase.data.PopularMovieBrief
import com.deerhunter.themoviedatabase.ui.base.injectViewModel
import com.deerhunter.themoviedatabase.ui.extensions.loadImage
import com.deerhunter.themoviedatabase.ui.extensions.withArguments
import com.deerhunter.themoviedatabase.ui.moviedetails.di.DaggerMovieDetailsComponent
import kotlinx.android.synthetic.main.movie_details_fragment.*
import kotlinx.android.synthetic.main.movie_details_fragment.moviePoster
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

    private fun showMovieDetails(result: MovieDetailsViewModel.MovieResult?) {
        when (result) {
            is MovieDetailsViewModel.MovieResult.Content -> showMovieDetails(result.movie)
            is MovieDetailsViewModel.MovieResult.Loading -> showProgress()
            is MovieDetailsViewModel.MovieResult.Error -> showErrorLayout(result.ex)
        }
    }

    private fun showMovieDetails(movie: Movie) {
        hideProgress()
        hideErrorLayout()
        contentLayout.visibility = View.VISIBLE
        with(movie) {
            moviePoster.loadImage(posterPath)
            movieTitle.text = originalTitle
            movieOverview.text = overview
            movieDetails.text =
                getString(R.string.movie_description_format, releaseDate, popularity, voteAverage)
        }
    }

    private fun showProgress() {
        hideErrorLayout()
        hideContentLayout()
        progressBar.show()
    }

    private fun showErrorLayout(ex: Throwable) {
        Timber.d(ex, "problem to show movie info")
        hideProgress()
        hideContentLayout()
        errorLayout.visibility = View.VISIBLE
        errorInfo.text = getString(R.string.problem_to_show_movie_info)
    }

    private fun hideContentLayout() {
        contentLayout.visibility = View.GONE
    }

    private fun hideProgress() {
        progressBar.hide()
    }

    private fun hideErrorLayout() {
        errorLayout.visibility = View.GONE
    }

    companion object {
        private const val ID = "id"

        fun newInstance(popularMovie: PopularMovieBrief) =
            MovieDetailsFragment().withArguments(ID to popularMovie.id)
    }
}