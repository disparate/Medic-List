package com.kazarovets.mediclist.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.kazarovets.mediclist.R
import com.kazarovets.mediclist.base.fragment.BaseVMFragment
import com.kazarovets.mediclist.activity.di.ActivityComponent
import com.kazarovets.mediclist.addperson.AddPersonFragment
import com.kazarovets.mediclist.app.di.AppComponent
import com.kazarovets.mediclist.home.di.DaggerHomeComponent
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : BaseVMFragment<HomeViewModel>() {


    override val layoutResId: Int
        get() = R.layout.home_fragment

    override fun getViewModelClass() = HomeViewModel::class

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tabs.setupWithViewPager(categoriesPager)

        val adapter = HomeCategoriesAdapter(childFragmentManager, requireContext())
        categoriesPager.adapter = adapter

        viewModel.tabs.observe(viewLifecycleOwner, Observer {
            adapter.setItems(it)
        })

        setupAddButton()
    }

    private fun setupAddButton() {
        homeAddFab.setOnClickListener {
            viewModel.onAddPersonClick()
        }
    }


    override fun injectDependencies(
        activityComponent: ActivityComponent
    ) {
        DaggerHomeComponent.builder()
            .activityComponent(activityComponent)
            .build()
            .inject(this)
    }
}