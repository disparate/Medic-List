package com.kazarovets.mediclist.smearstable.di

import com.kazarovets.mediclist.activity.di.ActivityComponent
import com.kazarovets.mediclist.app.di.vm.ViewModelProviderModule
import com.kazarovets.mediclist.smearstable.SmearsTableFragment
import dagger.Component

@SmearsTableScope
@Component(
    dependencies = [ActivityComponent::class],
    modules = [SmearsTableModule::class, ViewModelProviderModule::class]
)
interface SmearsTableComponent {
    fun inject(fgt: SmearsTableFragment)
}