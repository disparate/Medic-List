package com.kazarovets.mediclist.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*


inline fun <reified T : ViewModel> Fragment.getViewModel(vmFactory: ViewModelProvider.Factory): T {
    return ViewModelProviders.of(this, vmFactory).get(T::class.java)
}


inline fun <reified T : ViewModel> FragmentActivity.getViewModel(vmFactory: ViewModelProvider.Factory): T {
    return ViewModelProviders.of(this, vmFactory).get(T::class.java)
}

fun <T> LiveData<T>.observeNotNull(owner: LifecycleOwner, observer: (T) -> Unit) {
    this.observe(owner, Observer { it?.let(observer)})
}