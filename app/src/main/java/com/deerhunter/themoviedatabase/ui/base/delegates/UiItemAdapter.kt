package com.deerhunter.themoviedatabase.ui.base.delegates

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class UiItemAdapter(vararg delegates: AdapterDelegate<List<UiItem>>) : AsyncListDifferDelegationAdapter<UiItem>(DIFF_CALLBACK) {
    init {
        delegates.forEach { delegatesManager.addDelegate(it) }
        delegatesManager.fallbackDelegate = fallbackAdapterDelegate()
    }

    companion object {
        private val DIFF_CALLBACK: ItemCallback<UiItem> = object : ItemCallback<UiItem>() {
            override fun areItemsTheSame(oldItem: UiItem, newItem: UiItem): Boolean {
                return oldItem.id == newItem.id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: UiItem, newItem: UiItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}
