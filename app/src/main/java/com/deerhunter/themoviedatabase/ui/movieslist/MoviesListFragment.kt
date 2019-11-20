package com.deerhunter.themoviedatabase.ui.movieslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.deerhunter.themoviedatabase.R
import com.deerhunter.themoviedatabase.ui.base.delegates.UiItemAdapter
import com.deerhunter.themoviedatabase.ui.base.delegates.movieBriefAdapterDelegate
import com.deerhunter.themoviedatabase.ui.base.injectViewModel
import com.deerhunter.themoviedatabase.ui.movieslist.di.DaggerMoviesListComponent
import kotlinx.android.synthetic.main.movies_list_fragment.*
import me.vponomarenko.injectionmanager.x.XInjectionManager
import timber.log.Timber
import javax.inject.Inject

class MoviesListFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MoviesListViewModel

    private val adapter = UiItemAdapter(
        movieBriefAdapterDelegate {
            Timber.d("Clicked ${it.movieBrief.title}")
            doShuffle()
        })

    private fun doShuffle() {
        val items = adapter.items.toMutableList()
        items.shuffle()
        adapter.items = items
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerMoviesListComponent.builder()
            .iMoviesRepositoryProvider(XInjectionManager.findComponent()).build().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movies_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moviesList.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = injectViewModel(viewModelFactory)
        viewModel.moviesLiveData.observe(viewLifecycleOwner, Observer { movies ->
            adapter.items = movies
        })
    }

    companion object {
        fun newInstance() = MoviesListFragment()
    }
}
