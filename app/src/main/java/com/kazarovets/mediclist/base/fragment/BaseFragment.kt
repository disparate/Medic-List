package com.kazarovets.mediclist.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kazarovets.mediclist.activity.MainActivity

abstract class BaseFragment : Fragment() {

    protected abstract val layoutResId: Int

    protected open val isSystemBarVisible: Boolean = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutResId, container, false)
    }

    open fun onBackPressed(): Boolean {
        return false
    }

}