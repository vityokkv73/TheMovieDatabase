package com.deerhunter.themoviedatabase.ui.extensions

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

fun <T : Fragment> T.withArguments(vararg params: Pair<String, Any>): T {
    arguments = bundleOf(*params)
    return this
}