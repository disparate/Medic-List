package com.kazarovets.mediclist.addperson.di

import com.kazarovets.mediclist.activity.di.ActivityComponent
import com.kazarovets.mediclist.addperson.AddPersonDialogFragment
import com.kazarovets.mediclist.app.di.vm.ViewModelProviderModule
import com.kazarovets.mediclist.category.CategoryFragment
import dagger.Component


@AddPersonScope
@Component(
    dependencies = [ActivityComponent::class],
    modules = [AddPersonModule::class, ViewModelProviderModule::class]
)
interface AddPersonComponent {
    fun inject(fgt: AddPersonDialogFragment)
}