package com.deerhunter.themoviedatabase.ui.base.delegates

import com.deerhunter.themoviedatabase.R
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateLayoutContainer
import kotlinx.android.synthetic.main.fallback.*

fun fallbackAdapterDelegate() =
    adapterDelegateLayoutContainer<UiItem, UiItem>(
        R.layout.fallback
    ) {
        bind { diffPayloads ->
            reason.text = getString(R.string.cant_render_for_type, item.javaClass)
        }
    }