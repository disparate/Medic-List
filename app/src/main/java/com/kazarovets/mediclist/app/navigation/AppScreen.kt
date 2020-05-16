package com.kazarovets.mediclist.app.navigation


import android.content.Context
import android.content.Intent

import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.Screen
import ru.terrakok.cicerone.android.support.SupportAppScreen

/**
 * AppScreen is base class for description and creation application screen.<br></br>
 * NOTE: If you have described the creation of Intent then Activity will be started.<br></br>
 * Recommendation: Use Intents for launch external application.
 */
abstract class AppScreen : SupportAppScreen() {

    override fun getActivityIntent(context: Context): Intent? {
        return null
    }
}