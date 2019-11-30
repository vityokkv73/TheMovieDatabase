package com.deerhunter.themoviedatabase.ui.base.delegates

import com.deerhunter.themoviedatabase.R
import com.deerhunter.themoviedatabase.ui.extensions.loadImage
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateLayoutContainer
import kotlinx.android.synthetic.main.movie_brief.*

fun movieBriefAdapterDelegate(itemClickedListener: (MovieBriefUiItem) -> Unit) =
    adapterDelegateLayoutContainer<MovieBriefUiItem, UiItem>(R.layout.movie_brief) {
        itemView.setOnClickListener { itemClickedListener(item) }
        bind { diffPayloads ->
            with(item.popularMovieBrief) {
                movieName.text = title
                moviePoster.loadImage(posterPath)
            }
        }
    }