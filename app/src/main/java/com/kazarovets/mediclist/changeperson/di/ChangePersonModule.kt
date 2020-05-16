package com.kazarovets.mediclist.changeperson.di

import androidx.lifecycle.ViewModel
import com.kazarovets.mediclist.app.di.vm.ViewModelKey
import com.kazarovets.mediclist.changeperson.ChangePersonViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ChangePersonModule{

    @Binds
    @IntoMap
    @ViewModelKey(ChangePersonViewModel::class)
    internal abstract fun vm(vm: ChangePersonViewModel): ViewModel
}