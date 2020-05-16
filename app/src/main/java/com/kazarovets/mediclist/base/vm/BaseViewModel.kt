package com.kazarovets.mediclist.base.vm

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    protected var disposable: Disposable? = null

    protected var compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
        compositeDisposable.clear()
    }

}