package com.kazarovets.mediclist.smearstable.adapter

import com.kazarovets.mediclist.base.recycler.UniqueRecyclerItem

data class SmearsTableElement(
    val personName: String,
    val smears: List<String>
) : UniqueRecyclerItem {
    override fun areItemsTheSame(item: UniqueRecyclerItem): Boolean {
        return this == item as? SmearsTableElement
    }
}