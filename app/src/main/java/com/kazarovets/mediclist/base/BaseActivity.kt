package com.kazarovets.mediclist.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kazarovets.mediclist.R
import com.kazarovets.mediclist.base.fragment.BaseFragment

abstract class BaseActivity : AppCompatActivity() {

    protected abstract val layoutId: Int

    protected open val currFragment: Fragment?
        get() = supportFragmentManager.findFragmentById(R.id.mainActivityFragmentContainer)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(layoutId)
    }

    override fun onBackPressed() {
        val fragment = currFragment as? BaseFragment?

        if (fragment?.onBackPressed() != true) {
            super.onBackPressed()
        }
    }

}