package com.kazarovets.mediclist.activity

import android.app.Application
import com.kazarovets.mediclist.app.navigation.AppRouter
import com.kazarovets.mediclist.app.navigation.Screens
import com.kazarovets.mediclist.base.vm.BaseViewModel
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    app: Application,
    private val router: AppRouter
) : BaseViewModel() {

    init {
        router.newRootChain(Screens.home())
    }
}