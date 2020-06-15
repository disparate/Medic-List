package com.kazarovets.mediclist.smearstable

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.kazarovets.mediclist.R
import com.kazarovets.mediclist.activity.di.ActivityComponent
import com.kazarovets.mediclist.base.fragment.BaseVMFragment
import com.kazarovets.mediclist.smearstable.adapter.SmearsTableAdapter
import com.kazarovets.mediclist.smearstable.di.DaggerSmearsTableComponent
import kotlinx.android.synthetic.main.smears_table_fragment.*
import kotlin.reflect.KClass

class SmearsTableFragment : BaseVMFragment<SmearsTableViewModel>() {


    override fun getViewModelClass() = SmearsTableViewModel::class


    override val layoutResId: Int
        get() = R.layout.smears_table_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        smearsTableCloseButton.setOnClickListener {
            viewModel.onClose()
        }

        setupAdapter()

    }

    private fun setupAdapter() {
        val adapter = SmearsTableAdapter()
        smearsTableRecycler.layoutManager = LinearLayoutManager(context)
        smearsTableRecycler.adapter = adapter

        viewModel.smearsTable.observe(this, Observer {
            adapter.submitList(it)
        })
    }

    override fun injectDependencies(activityComponent: ActivityComponent) {
        DaggerSmearsTableComponent.builder()
            .activityComponent(activityComponent)
            .build()
            .inject(this)
    }
}