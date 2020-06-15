package com.kazarovets.mediclist.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.kazarovets.mediclist.R
import com.kazarovets.mediclist.activity.di.ActivityComponent
import com.kazarovets.mediclist.activity.di.ActivityModule
import com.kazarovets.mediclist.activity.di.DaggerActivityComponent
import com.kazarovets.mediclist.app.navigation.AppNavigator
import com.kazarovets.mediclist.app.navigation.LifecycleAwareNavigationHolder
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

        checkPermission()
    }


    val activityComponent: ActivityComponent by lazy {
        DaggerActivityComponent.builder()
            .appComponent(appComponent)
            .activityModule(ActivityModule(this))
            .build()
    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
            || ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.WRITE_CONTACTS
                ),
                CONTACTS_PERMISSIONS_REQ_CODE
            )
        } else { // Permission has already been granted
            viewModel.onContactsPermissionGranted()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == CONTACTS_PERMISSIONS_REQ_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                viewModel.onContactsPermissionGranted()
            } else {
                Toast.makeText(
                    this,
                    "Дайте разрешение, чтоб контакты синхронизировать!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


    companion object {
        private const val CONTACTS_PERMISSIONS_REQ_CODE = 1234
    }
}