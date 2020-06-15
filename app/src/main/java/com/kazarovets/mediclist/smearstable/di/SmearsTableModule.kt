package com.kazarovets.mediclist.smearstable.di

import androidx.lifecycle.ViewModel
import com.kazarovets.mediclist.app.di.vm.ViewModelKey
import com.kazarovets.mediclist.smearstable.SmearsTableViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SmearsTableModule{

    @Binds
    @IntoMap
    @ViewModelKey(SmearsTableViewModel::class)
    internal abstract fun viewModel(vm: SmearsTableViewModel): ViewModel
}