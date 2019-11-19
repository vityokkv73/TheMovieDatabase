package com.deerhunter.themoviedatabase.ui.movieslist

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.deerhunter.themoviedatabase.R
import com.deerhunter.themoviedatabase.ui.base.injectViewModel
import timber.log.Timber
import javax.inject.Inject

class MoviesListFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MoviesListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerMoviesListComponent.create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movies_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = injectViewModel(viewModelFactory)
        viewModel.moviesLiveData.observe(viewLifecycleOwner, Observer { movies ->
            Timber.d("list size = ${movies.size}")
        })
    }

    companion object {
        fun newInstance() = MoviesListFragment()
    }
}
