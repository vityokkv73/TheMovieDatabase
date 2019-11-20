package com.deerhunter.themoviedatabase.ui.base.delegates

import com.deerhunter.themoviedatabase.data.MovieBrief

open class UiItem(val id: Int)

data class MovieBriefUiItem(val movieBrief: MovieBrief): UiItem(movieBrief.id)