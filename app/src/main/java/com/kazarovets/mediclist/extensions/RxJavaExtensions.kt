package com.kazarovets.mediclist.extensions

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers


fun <T, R> Single<List<T>>.mapList(func: (T) -> R): Single<List<R>> {
    return this.map { list -> list.map { func(it) } }
}

fun <T, R> Observable<List<T>>.mapList(func: (T) -> R): Observable<List<R>> {
    return this.map { list -> list.map { func(it) } }
}

fun <U, D> Observable<U>.mapNotNull(mapFunc: (U) -> D?): Observable<D> = flatMap {
    mapFunc(it)?.let { Observable.just(it) } ?: Observable.empty()
}

fun <T> Observable<T>.observeOnUI(): Observable<T> {
    return this.observeOn(AndroidSchedulers.mainThread())
}


//fun <T> RxValue<Set<T>>.changeSet(changeFunc: MutableSet<T>.() -> Unit) {
//    set(
//        get()
//            .toMutableSet()
//            .apply { changeFunc.invoke(this) }
//    )
//}