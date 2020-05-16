package com.kazarovets.mediclist.app

import android.annotation.SuppressLint
import android.app.Application
import com.kazarovets.mediclist.app.di.AppComponent
import com.kazarovets.mediclist.app.di.AppModule
import com.kazarovets.mediclist.app.di.DaggerAppComponent
import com.kazarovets.mediclist.app.log.DebugTimberTree
import timber.log.Timber

class App : Application() {

    private lateinit var appComponent: AppComponent
    fun getAppComponent() = appComponent


    @SuppressLint("CheckResult")
    override fun onCreate() {
        super.onCreate()
        initAppComponent()
        initLoggers()
    }


    private fun initLoggers() {
        Timber.plant(DebugTimberTree())
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}