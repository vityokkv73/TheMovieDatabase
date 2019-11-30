package com.deerhunter.themoviedatabase.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.deerhunter.themoviedatabase.R
import com.deerhunter.themoviedatabase.navigation.screen.MoviesListScreen
import com.deerhunter.themoviedatabase.ui.base.injectViewModel
import com.deerhunter.themoviedatabase.ui.splash.di.DaggerSplashComponent
import kotlinx.android.synthetic.main.splash_fragment.*
import me.vponomarenko.injectionmanager.x.XInjectionManager
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class SplashFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var router: Router

    private lateinit var viewModel: SplashViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = injectViewModel(viewModelFactory)
        viewModel.liveData.observe(viewLifecycleOwner, Observer { status ->
            processStatus(status)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerSplashComponent.builder()
            .iConfigurationRepositoryProvider(XInjectionManager.findComponent())
            .iNavigationProvider(XInjectionManager.findComponent())
            .build().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.splash_fragment, container, false)
    }

    private fun processStatus(status: SplashViewModel.State) {
        when (status) {
            is SplashViewModel.State.Loading -> progressBar.show()
            is SplashViewModel.State.Error -> progressBar.hide()
            is SplashViewModel.State.Loaded -> router.newRootScreen(MoviesListScreen())
        }
    }

    companion object {
        fun newInstance() = SplashFragment()
    }
}