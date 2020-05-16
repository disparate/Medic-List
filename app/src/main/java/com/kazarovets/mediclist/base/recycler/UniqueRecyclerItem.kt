package com.kazarovets.mediclist.base.recycler

import androidx.recyclerview.widget.DiffUtil

interface UniqueRecyclerItem {

    fun areItemsTheSame(item: UniqueRecyclerItem): Boolean

    fun areContentsTheSame(item: UniqueRecyclerItem): Boolean = areItemsTheSame(item)
}

open class UniqueRecyclerItemDiffCallback<T : UniqueRecyclerItem> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.areItemsTheSame(newItem)
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.areContentsTheSame(newItem)
    }

}
