package com.kazarovets.mediclist.extensions

import android.content.Context
import android.graphics.Point
import android.view.Display
import android.view.WindowManager
import androidx.annotation.IntegerRes
import androidx.core.content.ContextCompat
import com.kazarovets.mediclist.app.App
import com.kazarovets.mediclist.app.di.AppComponent


val Context.appComponent: AppComponent
    get() = (this.applicationContext as App).getAppComponent()

fun Context.getColorCompat(res: Int): Int {
    return ContextCompat.getColor(this, res)
}

fun Context.getDimension(id: Int): Int {
    return resources.getDimensionPixelOffset(id)
}


fun Context.getDeviceSize(): Point {
    return try { // the only way to receive real device sizes without decorations (status bars etc.)
        val display =
            (getSystemService(Context.WINDOW_SERVICE) as WindowManager).getDefaultDisplay()
        val realSize = Point()
        Display::class.java.getMethod("getRealSize", Point::class.java).invoke(display, realSize)
        realSize
    } catch (e: Exception) {
        Point(resources.displayMetrics.widthPixels, resources.displayMetrics.heightPixels)
    }
}

fun Context.getInteger(@IntegerRes id: Int): Int {
    return resources.getInteger(id)
}
