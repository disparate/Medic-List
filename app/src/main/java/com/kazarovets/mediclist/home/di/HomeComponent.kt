package com.kazarovets.mediclist.home.di

import com.kazarovets.mediclist.activity.di.ActivityComponent
import com.kazarovets.mediclist.app.di.vm.ViewModelProviderModule
import com.kazarovets.mediclist.addperson.di.AddPersonScope
import com.kazarovets.mediclist.home.HomeFragment
import dagger.Component


@AddPersonScope
@Component(
    dependencies = [ActivityComponent::class],
    modules = [HomeModule::class, ViewModelProviderModule::class]
)
interface HomeComponent {
    fun inject(fgt: HomeFragment)
}