package com.kazarovets.mediclist.addperson.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import androidx.core.view.children
import androidx.core.view.size
import com.kazarovets.mediclist.R
import kotlinx.android.synthetic.main.add_person_smear_item.view.*
import kotlinx.android.synthetic.main.add_person_smears.view.*
import timber.log.Timber

class SmearsView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        orientation = VERTICAL

        LayoutInflater.from(context).inflate(R.layout.add_person_smears, this, true)

        smearsAddNew.setOnClickListener {
            addNewSmear()
        }
    }

    fun getSmears(): List<String> {
        return smearsContainer.children.map {
            (it as SmearViewItem).smearText
        }.toList()
    }

    fun setSmears(smears: List<String>) {
        smearsContainer.removeAllViews()
        smears.forEachIndexed { index, value ->
            addSmearView(index + 1, value)
        }
    }

    private fun addNewSmear() {
        addSmearView(smearsContainer.childCount + 1, "")
    }

    private fun addSmearView(index: Int, text: String) {
        val v = SmearViewItem(context)
        v.setSmear(number = index, text = text)
        v.onCloseListener = {
            smearsContainer.removeView(v)
            recalculateSmearsIndexes()
        }
        smearsContainer.addView(v, ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT))
    }

    private fun recalculateSmearsIndexes() {
        smearsContainer.children.forEachIndexed { index, v ->
            (v as SmearViewItem).setNumber(index + 1)
        }
    }
}

class SmearViewItem @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    var onCloseListener: () -> Unit = { Timber.w("no close listener set") }

    val smearText: String
        get() = smearItemEditText.getText()

    init {
        LayoutInflater.from(context).inflate(R.layout.add_person_smear_item, this, true)
        smearItemClose.setOnClickListener { onCloseListener() }
    }

    fun setSmear(number: Int, text: String) {
        setNumber(number)
        smearItemEditText.setText(text)
    }

    fun setNumber(number: Int) {
        smearItemEditText.title = "#$number"
    }
}