package com.kazarovets.mediclist.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.kazarovets.mediclist.activity.di.ActivityModule
import com.kazarovets.mediclist.app.navigation.AppNavigator
import com.kazarovets.mediclist.app.navigation.LifecycleAwareNavigationHolder
import com.kazarovets.mediclist.R
import com.kazarovets.mediclist.activity.di.ActivityComponent
import com.kazarovets.mediclist.activity.di.DaggerActivityComponent
import com.kazarovets.mediclist.extensions.appComponent
import com.kazarovets.mediclist.extensions.getViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    protected lateinit var navigatorHolder: LifecycleAwareNavigationHolder

    @Inject
    protected lateinit var viewModelProvider: ViewModelProvider.Factory

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity)

        activityComponent.inject(this)
        viewModel = getViewModel(viewModelProvider)

        navigatorHolder.setNavigatorAndRegister(
            AppNavigator(this, supportFragmentManager, R.id.mainActivityFragmentContainer),
            this
        )
    }


    val activityComponent: ActivityComponent by lazy {
        DaggerActivityComponent.builder()
            .appComponent(appComponent)
            .activityModule(ActivityModule(this))
            .build()
    }
}