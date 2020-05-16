package com.kazarovets.mediclist.base.fragment

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.view.Gravity.CENTER
import androidx.fragment.app.DialogFragment
import timber.log.Timber
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import com.kazarovets.mediclist.R
import com.kazarovets.mediclist.extensions.dpToPx
import java.lang.Exception


abstract class BaseDialogFragment : DialogFragment() {

    abstract val layoutId: Int

    protected open val isDialogCancelable: Boolean
        get() = true

    var onCancelListener: () -> Unit = {}

    open val customAnimationStyle: Int? = null

    protected open fun getDialogHeight(context: Context) = MATCH_PARENT

    protected open fun getDialogWidth(context: Context) = 340.dpToPx()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return object : Dialog(activity!!, theme) {
            override fun onBackPressed() {
                if (isDialogCancelable) {
                    closeDialog()
                }
            }
        }.apply {
            isCancelable = isDialogCancelable
            setCanceledOnTouchOutside(isDialogCancelable)

        }
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window?.setLayout(width, height)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflateViews(inflater, container)

        dialog?.window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            requestFeature(Window.FEATURE_NO_TITLE)
            setFlags(
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
            )
            decorView.systemUiVisibility = activity!!.window.decorView.systemUiVisibility

            setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

            dialog!!.setOnShowListener {
                //Clear the not focusable flag from the window
                dialog?.window?.let { window ->
                    window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)

                    //Update the WindowManager with the new attributes (no nicer way I know of to do this)..
                    val wm =
                        activity?.getSystemService(Context.WINDOW_SERVICE) as? WindowManager
                    if (window.decorView.isAttachedToWindow) {
                        wm?.updateViewLayout(window.decorView, window.attributes)
                    }
                }
            }

        }

        customAnimationStyle?.let {
            dialog?.window?.attributes?.windowAnimations = it
        }

        return view
    }

    private fun inflateViews(inflater: LayoutInflater, container: ViewGroup?): View {
        val dialogContainer =
            inflater.inflate(R.layout.base_dialog, container, false) as FrameLayout
        val view = inflater.inflate(layoutId, dialogContainer, false)
        val dialogHeight = getDialogHeight(dialogContainer.context)
        val dialogWidth = getDialogWidth(dialogContainer.context)

        dialogContainer.setOnClickListener {
            if (isDialogCancelable)
                closeDialog()
        }
        view.setOnClickListener {
            //don't allow dismiss on click on view
        }

        dialogContainer.addView(view, FrameLayout.LayoutParams(dialogWidth, dialogHeight, CENTER))
        return dialogContainer
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onCancelListener()
    }

    fun closeDialog() {
        try {
            dismissAllowingStateLoss()
        } catch (e: Exception) {
            Timber.e(e)
        }
    }
}