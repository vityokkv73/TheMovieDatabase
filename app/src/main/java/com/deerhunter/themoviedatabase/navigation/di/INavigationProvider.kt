package com.deerhunter.themoviedatabase.navigation.di

import ru.terrakok.cicerone.Router

interface INavigationProvider {
    fun provideRouter(): Router
}