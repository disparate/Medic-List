package com.kazarovets.mediclist.activity.di

import android.app.Activity
import android.app.Application
import androidx.lifecycle.ViewModel
import com.kazarovets.mediclist.app.navigation.AppRouter
import com.kazarovets.mediclist.activity.MainActivityViewModel
import com.kazarovets.mediclist.app.di.vm.ViewModelKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class ActivityModule(val activity: Activity) {

    @Provides
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    fun mainVM(
        app: Application,
        router: AppRouter
    ): ViewModel {
        return MainActivityViewModel(app, router)
    }
}