package com.kazarovets.mediclist.app.di.navigation

import com.kazarovets.mediclist.app.navigation.AppRouter
import com.kazarovets.mediclist.app.navigation.LifecycleAwareNavigationHolder

interface NavigationProvider {
    fun provideNavigatorHolder(): LifecycleAwareNavigationHolder
    fun provideRouter(): AppRouter
}
