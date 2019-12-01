package com.deerhunter.themoviedatabase.navigation.screen

import androidx.fragment.app.Fragment
import com.deerhunter.themoviedatabase.data.PopularMovieBrief
import com.deerhunter.themoviedatabase.ui.moviedetails.MovieDetailsFragment
import com.deerhunter.themoviedatabase.ui.movieslist.MoviesListFragment
import com.deerhunter.themoviedatabase.ui.splash.SplashFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class MoviesListScreen: SupportAppScreen() {
    override fun getFragment(): Fragment {
        return MoviesListFragment.newInstance()
    }
}

class SplashScreen: SupportAppScreen() {
    override fun getFragment(): Fragment {
        return SplashFragment.newInstance()
    }
}

class MovieDetailsScreen(private val popularMovie: PopularMovieBrief): SupportAppScreen() {
    override fun getFragment(): Fragment {
        return MovieDetailsFragment.newInstance(popularMovie)
    }
}