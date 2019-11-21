package com.deerhunter.themoviedatabase.ui.base.delegates

import com.deerhunter.themoviedatabase.data.PopularMovieBrief

open class UiItem(val id: Int)

data class MovieBriefUiItem(val popularMovieBrief: PopularMovieBrief): UiItem(popularMovieBrief.id)