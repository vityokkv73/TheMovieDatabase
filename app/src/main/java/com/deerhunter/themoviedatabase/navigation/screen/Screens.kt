package com.deerhunter.themoviedatabase.navigation.screen

import androidx.fragment.app.Fragment
import com.deerhunter.themoviedatabase.ui.movieslist.MoviesListFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class MoviesListScreen: SupportAppScreen() {
    override fun getFragment(): Fragment {
        return MoviesListFragment.newInstance()
    }
}