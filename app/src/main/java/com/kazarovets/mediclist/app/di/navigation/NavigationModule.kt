package com.kazarovets.mediclist.app.di.navigation

import com.kazarovets.mediclist.app.navigation.AppRouter
import com.kazarovets.mediclist.app.navigation.LifecycleAwareNavigationHolder
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import javax.inject.Singleton

@Module
class NavigationModule {

    @Provides
    @Singleton
    fun cicerone(): Cicerone<AppRouter> {
        return Cicerone.create(AppRouter())
    }

    @Provides
    @Singleton
    fun router(cicerone: Cicerone<AppRouter>): AppRouter {
        return cicerone.router
    }

    @Provides
    @Singleton
    fun navigatorHolder(cicerone: Cicerone<AppRouter>): LifecycleAwareNavigationHolder {
        return LifecycleAwareNavigationHolder(cicerone.navigatorHolder)
    }
}
