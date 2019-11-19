package com.deerhunter.themoviedatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.deerhunter.themoviedatabase.navigation.di.DaggerNavigationComponent
import com.deerhunter.themoviedatabase.navigation.screen.MoviesListScreen
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: Router

    private val navigator = SupportAppNavigator(this, R.id.fragmentContainerView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerNavigationComponent.create().inject(this)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
        if (supportFragmentManager.fragments.isEmpty()) {
            router.navigateTo(MoviesListScreen())
        }
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}