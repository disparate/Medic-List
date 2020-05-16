package com.kazarovets.mediclist.app.di.vm

import androidx.lifecycle.ViewModelProvider
import com.kazarovets.mediclist.app.di.vm.AppViewModelFactory
import dagger.Binds
import dagger.Module


/**
 * This must should be added to every component that contains a module with ViewModel class
 *
 * View Model should be added in screen-related module
 * with
 * @IntoMap
 * @ViewModelKey(ViewModel::class)
 * annotations
 */
@Module
abstract class ViewModelProviderModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory

}