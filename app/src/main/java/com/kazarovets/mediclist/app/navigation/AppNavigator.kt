package com.kazarovets.mediclist.app.navigation


import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import timber.log.Timber

class AppNavigator(
    activity: FragmentActivity,
    fragmentManager: FragmentManager,
    containerId: Int
) : SupportAppNavigator(activity, fragmentManager, containerId) {

    override fun applyCommands(commands: Array<Command>) {
        if (activity.isDestroyed.not()) {
            try {
                super.applyCommands(commands)
            } catch (e: Exception) {
                Timber.w(e)
            }
        }
    }

}
