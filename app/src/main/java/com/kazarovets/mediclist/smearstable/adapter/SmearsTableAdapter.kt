package com.kazarovets.mediclist.smearstable.adapter

import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.core.view.setPadding
import androidx.databinding.BindingAdapter
import com.kazarovets.mediclist.R
import com.kazarovets.mediclist.addperson.view.SmearViewItem
import com.kazarovets.mediclist.base.recycler.BaseBindingAdapter
import com.kazarovets.mediclist.base.recycler.BindableViewHolder
import com.kazarovets.mediclist.base.recycler.UniqueRecyclerItemDiffCallback
import com.kazarovets.mediclist.extensions.dpToPx
import com.kazarovets.mediclist.extensions.getColorCompat
import kotlinx.android.synthetic.main.add_person_smear_item.view.*

class SmearsTableAdapter : BaseBindingAdapter<SmearsTableElement>(SmearsTableDiffCallback()) {
    override fun getLayoutResId(viewType: Int): Int {
        return R.layout.smears_table_item
    }

    override fun onBindViewHolder(holder: BindableViewHolder<*>, position: Int) {
        super.onBindViewHolder(holder, position)

        holder.itemView.setBackgroundColor(
            holder.context.getColorCompat(
                when (position % 2) {
                    0 -> R.color.palette_white
                    else -> R.color.palette_light_grey
                }
            )
        )
    }
}

class SmearsTableDiffCallback : UniqueRecyclerItemDiffCallback<SmearsTableElement>() {

}


@BindingAdapter("tableSmears")
fun setTableSmears(vg: ViewGroup, smears: List<String>) {
    vg.removeAllViews()
    smears.forEachIndexed { index, value ->
        if (value.isNotEmpty()) {
            val v = TextView(vg.context)
            v.text = "#${index + 1} - $value"
            v.setPadding(4.dpToPx())
            v.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
            v.setTextColor(v.context.getColorCompat(R.color.palette_black))
            vg.addView(v, ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT))
        }
    }
}