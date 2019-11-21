package com.deerhunter.themoviedatabase.ui.base.delegates

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.paging.PagedListDelegationAdapter

class UiItemAdapter(vararg delegates: AdapterDelegate<List<UiItem>>) :
    PagedListDelegationAdapter<UiItem>(object : ItemCallback<UiItem>() {
        override fun areItemsTheSame(oldItem: UiItem, newItem: UiItem): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: UiItem, newItem: UiItem): Boolean {
            return oldItem == newItem
        }
    }, *delegates)
