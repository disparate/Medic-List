package com.kazarovets.mediclist.category.di

import android.app.Activity
import android.app.Application
import androidx.lifecycle.ViewModel
import com.kazarovets.mediclist.activity.MainActivityViewModel
import com.kazarovets.mediclist.app.di.vm.ViewModelKey
import com.kazarovets.mediclist.category.CategoryViewModel
import com.kazarovets.mediclist.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class CategoryModule{

    @Binds
    @IntoMap
    @ViewModelKey(CategoryViewModel::class)
    internal abstract fun homeVM(vm: CategoryViewModel): ViewModel
}