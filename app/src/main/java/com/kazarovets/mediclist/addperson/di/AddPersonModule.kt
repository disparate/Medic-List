package com.kazarovets.mediclist.addperson.di

import android.app.Activity
import android.app.Application
import androidx.lifecycle.ViewModel
import com.kazarovets.mediclist.activity.MainActivityViewModel
import com.kazarovets.mediclist.addperson.AddPersonViewModel
import com.kazarovets.mediclist.app.di.vm.ViewModelKey
import com.kazarovets.mediclist.category.CategoryViewModel
import com.kazarovets.mediclist.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class AddPersonModule{

    @Binds
    @IntoMap
    @ViewModelKey(AddPersonViewModel::class)
    internal abstract fun vm(vm: AddPersonViewModel): ViewModel
}