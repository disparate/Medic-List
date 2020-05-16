package com.kazarovets.mediclist.home

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.kazarovets.mediclist.R
import com.kazarovets.mediclist.category.CategoryFragment
import com.kazarovets.mediclist.category.bo.TabCategory

class HomeCategoriesAdapter(fragmentManager: FragmentManager,
                            private val context: Context) : FragmentStatePagerAdapter(
    fragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {

    private var items: List<TabCategory> = emptyList()
    fun setItems(items: List<TabCategory>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getCount(): Int = items.size

    override fun getItem(position: Int): Fragment {
        return CategoryFragment.newInstance(items[position])
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.getString(when(items[position]) {
            TabCategory.COVID -> R.string.category_covid
            TabCategory.LEVEL1 -> R.string.category_level_1
            TabCategory.LEVEL2 -> R.string.category_level_2
            TabCategory.PROBABLE -> R.string.category_probable
            TabCategory.CLOSED -> R.string.category_closed
        })
    }

}