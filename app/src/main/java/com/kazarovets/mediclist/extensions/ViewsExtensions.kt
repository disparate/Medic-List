package com.kazarovets.mediclist.extensions

import android.content.res.ColorStateList
import android.content.res.Resources
import android.view.View
import android.view.ViewTreeObserver
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.core.view.ViewCompat

fun View.doAfterGlobalLayout(func: View.() -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            viewTreeObserver.removeOnGlobalLayoutListener(this)
            func()
        }
    })
}

fun Int.pxToDp(): Int = (this / Resources.getSystem().displayMetrics.density).toInt()

fun Int.dpToPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

fun ImageView.setTint(@ColorInt color: Int) {
    imageTintList = ColorStateList.valueOf(color)
}

fun View.getDimension(id: Int) = context.getDimension(id)

fun View.invalidateOnAnimationCompat() {
    ViewCompat.postInvalidateOnAnimation(this)
}

fun doIfAfterDelay(prevClick: Long?, delay: Int = 1000, func: (Long) -> Unit) {
    val now = System.currentTimeMillis()
    if (now > (prevClick ?: 0L) + delay) {
        func(now)
    }
}