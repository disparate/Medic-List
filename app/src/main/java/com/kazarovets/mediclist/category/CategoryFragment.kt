package com.kazarovets.mediclist.category

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.kazarovets.mediclist.R
import com.kazarovets.mediclist.activity.di.ActivityComponent
import com.kazarovets.mediclist.base.fragment.BaseVMFragment
import com.kazarovets.mediclist.category.bo.CategoryUIPerson
import com.kazarovets.mediclist.category.bo.TabCategory
import com.kazarovets.mediclist.category.di.DaggerCategoryComponent
import com.kazarovets.mediclist.category.recycler.CategoryRecyclerAdapter
import com.kazarovets.mediclist.changeperson.ChangePersonFragment
import com.kazarovets.mediclist.extensions.withArguments
import kotlinx.android.synthetic.main.category_fragment.*

class CategoryFragment : BaseVMFragment<CategoryViewModel>() {

    override val layoutResId: Int
        get() = R.layout.category_fragment

    override fun getViewModelClass() = CategoryViewModel::class

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabCategory =
            arguments?.getInt(ARG_CATEGORY)?.let { TabCategory.values()[it] } ?: return

        viewModel.init(tabCategory)

        setupRecycler()

    }

    private fun setupRecycler() {
        val adapter = CategoryRecyclerAdapter()
        categoryRecycler.layoutManager = LinearLayoutManager(context)
        categoryRecycler.adapter = adapter

        adapter.itemClickListener = {
            viewModel.openChangePerson(it.item)
        }

        viewModel.categoryPersons.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    override fun injectDependencies(
        activityComponent: ActivityComponent
    ) {
        DaggerCategoryComponent.builder()
            .activityComponent(activityComponent)
            .build()
            .inject(this)
    }

    companion object {

        private const val ARG_CATEGORY = "arg_category"

        fun newInstance(category: TabCategory) = CategoryFragment().withArguments {
            putInt(ARG_CATEGORY, category.ordinal)
        }

    }

}