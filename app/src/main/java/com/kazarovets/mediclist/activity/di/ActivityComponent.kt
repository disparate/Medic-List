package com.kazarovets.mediclist.activity.di

import com.kazarovets.mediclist.activity.MainActivity
import com.kazarovets.mediclist.app.di.AppComponent
import com.kazarovets.mediclist.app.di.vm.ViewModelProviderModule
import dagger.Component


@ActivityScope
@Component(
    dependencies = [AppComponent::class],
    modules = [ActivityModule::class, ViewModelProviderModule::class]
)
interface ActivityComponent: AppComponent {
    fun inject(activity: MainActivity)
}