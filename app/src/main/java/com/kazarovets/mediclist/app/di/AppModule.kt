package com.kazarovets.mediclist.app.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.kazarovets.mediclist.app.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: App) {

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return app
    }

    @Provides
    @Singleton
    fun prefs(app: Application): SharedPreferences {
        return app.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    companion object {
        const val PREFS_NAME = "Prefs"
    }
}
