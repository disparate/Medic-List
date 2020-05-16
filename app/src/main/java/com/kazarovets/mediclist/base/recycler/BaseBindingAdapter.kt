package com.kazarovets.mediclist.base.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.kazarovets.mediclist.R
import com.kazarovets.mediclist.BR



abstract class BaseBindingAdapter<DATA: Any>(callback: DiffUtil.ItemCallback<DATA>) :
    ListAdapter<DATA, BindableViewHolder<*>>(getDefaultAsyncDifferConfig(callback)) {

    var itemClickListener: ((ClickItem<DATA>) -> Unit)? = null

    open val hasInnerClickListener get() = true

    private val innerClickListener: View.OnClickListener = View.OnClickListener { v ->
        val holder = v.getTag(R.id.holder_tag) as BindableViewHolder<*>
        val position = holder.adapterPosition
        if (position >= 0) { //sometimes it happens
            val clickItem = ClickItem(holder.context, holder, position, getItem(position))
            itemClickListener?.invoke(clickItem)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindableViewHolder<*> =
        BindableViewHolder.create<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            getLayoutResId(viewType),
            parent
        )

    @LayoutRes
    protected abstract fun getLayoutResId(viewType: Int): Int

    override fun onBindViewHolder(holder: BindableViewHolder<*>, position: Int) {
        onBind(holder, getItem(position))
        holder.bindings.executePendingBindings()
        if (hasInnerClickListener) {
            holder.itemView.setOnClickListener(innerClickListener)
        }
        holder.itemView.setTag(R.id.holder_tag, holder)
    }

    public override fun getItem(position: Int): DATA {
        return super.getItem(position)
    }

    open fun onBind(holder: BindableViewHolder<*>, item: DATA) {
        holder.bindings.setVariable(BR.item, item)
    }


    protected fun changeListElement(old: DATA, new: DATA) {
        submitList(currentList.mapNotNull {
            if (it == old) {
                new
            } else {
                it
            }
        })
    }

    protected fun removeListElement(element: DATA) {
        submitList(currentList?.filter { it != element })
    }


    override fun submitList(list: List<DATA>?) {
        super.submitList(list)
    }

    companion object {

        private const val TAG_HOLDER_ID = 99

        @JvmStatic
        fun <T> getDefaultAsyncDifferConfig(callback: DiffUtil.ItemCallback<T>): AsyncDifferConfig<T> {
            return AsyncDifferConfig.Builder<T>(callback).build()
        }

    }

}