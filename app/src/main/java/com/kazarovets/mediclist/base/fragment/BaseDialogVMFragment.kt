package com.kazarovets.mediclist.base.fragment

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.kazarovets.mediclist.base.vm.BaseViewModel
import javax.inject.Inject
import kotlin.reflect.KClass
import com.kazarovets.mediclist.activity.MainActivity
import com.kazarovets.mediclist.activity.di.ActivityComponent


abstract class BaseDialogVMFragment<VM : BaseViewModel> : BaseDialogFragment() {

    @Inject
    protected lateinit var viewModelProvider: ViewModelProvider.Factory

    protected lateinit var viewModel: VM

    abstract fun getViewModelClass(): KClass<VM>

    protected open fun onVMCreated(vm: VM) {

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val activityComponent = (activity as? MainActivity)?.activityComponent
            ?: throw IllegalStateException("Fragment must be inserted in MainActivity")

        injectDependencies(activityComponent)

        viewModel = ViewModelProviders.of(this, viewModelProvider).get(getViewModelClass().java)
        onVMCreated(viewModel)
    }

    protected abstract fun injectDependencies(activityComponent: ActivityComponent)


}