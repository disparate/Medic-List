package com.kazarovets.mediclist.app.navigation

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import ru.terrakok.cicerone.NavigatorHolder
import androidx.lifecycle.LifecycleObserver
import ru.terrakok.cicerone.Navigator


open class LifecycleAwareNavigationHolder constructor(
    private val navigatorHolder: NavigatorHolder
) : LifecycleObserver {

    private var navigator: Navigator? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        navigator?.let { navigatorHolder.setNavigator(it) }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        navigatorHolder.removeNavigator()
    }

    fun setNavigatorAndRegister(navigator: Navigator,
                                lifecycleOwner: LifecycleOwner) {
        this.navigator = navigator
        lifecycleOwner.lifecycle.addObserver(this)
    }

    fun removeNavigator() {
        this.navigator = null
    }
}