package com.kazarovets.mediclist.base.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BindableViewHolder<out BINDING : ViewDataBinding>(val bindings: BINDING) : RecyclerView.ViewHolder(bindings.root) {

    companion object {

        fun <BINDING : ViewDataBinding> create(inflater: LayoutInflater,
                                               layoutResId: Int, parent: ViewGroup): BindableViewHolder<BINDING> {
            return BindableViewHolder(DataBindingUtil.inflate<BINDING>(inflater, layoutResId, parent, false))
        }
    }

    val context: Context get() = itemView.context
}