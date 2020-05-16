package com.kazarovets.mediclist.app.di

import android.app.Application
import com.kazarovets.mediclist.app.di.navigation.NavigationModule
import com.kazarovets.mediclist.app.di.navigation.NavigationProvider
import com.kazarovets.mediclist.app.di.vm.RepositoriesProvider
import com.kazarovets.mediclist.persons.di.PersonsDBModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AppModule::class, NavigationModule::class,
        PersonsDBModule::class
    ]
)
interface AppComponent : NavigationProvider, RepositoriesProvider {
    fun provideApp(): Application
}