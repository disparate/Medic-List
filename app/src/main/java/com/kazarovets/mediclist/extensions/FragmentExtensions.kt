package com.kazarovets.mediclist.extensions

import android.os.Bundle
import androidx.fragment.app.Fragment


fun <T : Fragment> T.withArguments(putFunc: Bundle.() -> Unit): T {
    return this.apply {
        arguments = Bundle().apply(putFunc)
    }
}