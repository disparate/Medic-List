package com.kazarovets.mediclist.category.di

import com.kazarovets.mediclist.activity.di.ActivityComponent
import com.kazarovets.mediclist.addperson.di.AddPersonModule
import com.kazarovets.mediclist.app.di.vm.ViewModelProviderModule
import com.kazarovets.mediclist.category.CategoryFragment
import com.kazarovets.mediclist.category.CategoryViewModel
import dagger.Component


@CategoryScope
@Component(
    dependencies = [ActivityComponent::class],
    modules = [CategoryModule::class, ViewModelProviderModule::class]
)
interface CategoryComponent {
    fun inject(fgt: CategoryFragment)
}