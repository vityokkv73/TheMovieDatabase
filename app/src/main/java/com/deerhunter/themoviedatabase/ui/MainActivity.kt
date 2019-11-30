package com.deerhunter.themoviedatabase.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.deerhunter.themoviedatabase.R
import com.deerhunter.themoviedatabase.navigation.di.DaggerNavigationComponent
import com.deerhunter.themoviedatabase.navigation.di.NavigationComponent
import com.deerhunter.themoviedatabase.navigation.screen.SplashScreen
import me.vponomarenko.injectionmanager.IHasComponent
import me.vponomarenko.injectionmanager.x.XInjectionManager
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: Router

    private val navigator = SupportAppNavigator(this,
        R.id.fragmentContainerView
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        XInjectionManager.bindComponent(object : IHasComponent<NavigationComponent> {
            override fun getComponent() =
                DaggerNavigationComponent.create()
        })
        XInjectionManager.findComponent<NavigationComponent>().inject(this)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
        if (supportFragmentManager.fragments.isEmpty()) {
            router.navigateTo(SplashScreen())
        }
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}