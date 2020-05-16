package com.kazarovets.mediclist.category.recycler

import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.kazarovets.mediclist.base.recycler.UniqueRecyclerItemDiffCallback
import com.kazarovets.mediclist.R
import com.kazarovets.mediclist.base.recycler.BaseBindingAdapter
import com.kazarovets.mediclist.category.bo.CategoryUIPerson
import com.kazarovets.mediclist.category.bo.CovidCategory
import com.kazarovets.mediclist.category.bo.TabCategory
import com.kazarovets.mediclist.extensions.getColorCompat

class CategoryRecyclerAdapter : BaseBindingAdapter<CategoryUIPerson>(
    CategoryPersonDiffCallback()
) {


    override fun getLayoutResId(viewType: Int): Int {
        return R.layout.category_person_item
    }
}

@BindingAdapter("bind:categoryShort")
fun bindStatus(view: TextView, person: CategoryUIPerson?) {
    if(person?.category == null) {
        view.isVisible = false
        return
    }

    view.setText(when(person.category) {
        CovidCategory.COVID -> R.string.category_covid_short
        CovidCategory.LEVEL1 -> R.string.category_level_1_short
        CovidCategory.LEVEL2 -> R.string.category_level_2_short
        CovidCategory.PROBABLE -> R.string.category_probable_short

    })

    view.setTextColor(view.context.getColorCompat(when(person.tabCategory) {
        TabCategory.COVID -> R.color.palette_red
        TabCategory.LEVEL1 -> R.color.palette_pink
        TabCategory.LEVEL2 -> R.color.palette_dark_purple
        TabCategory.PROBABLE -> R.color.palette_green
        TabCategory.CLOSED -> R.color.palette_black
    }))
}


class CategoryPersonDiffCallback : UniqueRecyclerItemDiffCallback<CategoryUIPerson>() {

}