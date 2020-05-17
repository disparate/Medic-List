package com.kazarovets.mediclist.base.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.kazarovets.mediclist.base.vm.BaseViewModel
import com.kazarovets.mediclist.activity.MainActivity
import com.kazarovets.mediclist.activity.di.ActivityComponent
import com.kazarovets.mediclist.app.di.AppComponent
import com.kazarovets.mediclist.extensions.appComponent
import javax.inject.Inject
import kotlin.reflect.KClass

abstract class BaseVMFragment<VM : BaseViewModel>: BaseFragment() {

    @Inject
    protected lateinit var viewModelProvider: ViewModelProvider.Factory

    protected lateinit var viewModel: VM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activityComponent = (activity as? MainActivity)?.activityComponent
            ?: throw IllegalStateException("Base fragment must be inserted in MainActivity")

        injectDependencies(activityComponent)

        viewModel = ViewModelProviders.of(this, viewModelProvider)
            .get(getViewModelClass().java)
    }

    abstract fun injectDependencies(activityComponent: ActivityComponent)

    abstract fun getViewModelClass(): KClass<VM>

    fun isVMInitialized() = ::viewModel.isInitialized
}