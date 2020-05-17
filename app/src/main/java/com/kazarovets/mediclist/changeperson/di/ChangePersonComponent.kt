package com.kazarovets.mediclist.changeperson.di

import com.kazarovets.mediclist.activity.di.ActivityComponent
import com.kazarovets.mediclist.app.di.vm.ViewModelProviderModule
import com.kazarovets.mediclist.changeperson.ChangePersonFragment
import dagger.Component


@ChangePersonScope
@Component(
    dependencies = [ActivityComponent::class],
    modules = [ChangePersonModule::class, ViewModelProviderModule::class]
)
interface ChangePersonComponent {
    fun inject(fgt: ChangePersonFragment)
}