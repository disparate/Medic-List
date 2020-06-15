package com.kazarovets.mediclist.addperson.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.core.view.children
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.kazarovets.mediclist.R
import com.kazarovets.mediclist.category.bo.CovidCategory
import timber.log.Timber

class CovidCategorySelector @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ChipGroup(context, attrs, defStyleAttr) {

    init {
        for (category in CovidCategory.values()) {
            addChipForCategory(category)
        }
    }

    var selectedCategory: CovidCategory? = null

    var onCategorySelected: (CovidCategory?) -> Unit = { Timber.w("no listener set") }

    fun selectCategory(category: CovidCategory) {
        check(category.ordinal)
    }


    private fun addChipForCategory(category: CovidCategory) {
        val chip = LayoutInflater.from(context).inflate(
            R.layout.add_person_category_chip,
            this,
            false
        ) as Chip

        chip.id = category.ordinal

        chip.text = context.getString(
            when (category) {
                CovidCategory.COVID -> R.string.category_covid
                CovidCategory.COVID_PNEUMONIA -> R.string.category_covid_pneumonia
                CovidCategory.PNEUMONIA -> R.string.category_pneumonia
                CovidCategory.LEVEL1 -> R.string.category_level_1
                CovidCategory.LEVEL2 -> R.string.category_level_2
                CovidCategory.PROBABLE -> R.string.category_probable
            }
        )

        chip.setOnCheckedChangeListener { compoundButton, checked ->
            if (checked) {
                selectedCategory = category
                onCategorySelected(category)
                this.children.filterIsInstance<Chip>()
                    .forEach { c ->
                        if (c != compoundButton) {
                            c.isChecked = false
                        }
                    }
            } else {
                if(selectedCategory == category) {
                    selectedCategory = null
                    onCategorySelected(null)
                }
            }
        }

        addView(chip)
    }
}