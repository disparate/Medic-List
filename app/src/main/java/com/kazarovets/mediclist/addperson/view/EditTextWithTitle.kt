package com.kazarovets.mediclist.addperson.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.widget.doOnTextChanged
import com.kazarovets.mediclist.R
import kotlinx.android.synthetic.main.edit_text_with_title.view.*
import timber.log.Timber

class EditTextWithTitle @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    var onTextChanged: (String?) -> Unit = { Timber.w("no text change listener") }

    init {
        LayoutInflater.from(context).inflate(R.layout.edit_text_with_title, this, true)
        orientation = HORIZONTAL
        attrs?.let { applyAttributes(it) }
        etEdit.doOnTextChanged { text, start, count, after ->
            onTextChanged(text?.toString())
        }
    }

    var title: String = ""
        set(value) {
            field = value
            etTitle.text = value
        }

    fun getText(): String {
        return etEdit.text.toString()
    }

    fun setText(text: String) {
        etEdit.setText(text)
    }



    private fun applyAttributes(attrs: AttributeSet) {
        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.EditTextWithTitle, 0, 0)
        try {
            a.getText(R.styleable.EditTextWithTitle_etTitle)?.let {
                title = it.toString()
            }
        } finally {
            a.recycle()
        }
    }
}