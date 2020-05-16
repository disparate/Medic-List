package com.kazarovets.mediclist.category.bo

import com.kazarovets.mediclist.base.recycler.UniqueRecyclerItem

data class CategoryUIPerson(
    val name: String,
    val id: Long,
    val phone: String?,
    val category: CovidCategory?,
    val tabCategory: TabCategory
) : UniqueRecyclerItem {

    override fun areContentsTheSame(item: UniqueRecyclerItem): Boolean {
        return this == (item as? CategoryUIPerson)
    }

    override fun areItemsTheSame(item: UniqueRecyclerItem): Boolean {
        return this.id == (item as? CategoryUIPerson)?.id
    }

}