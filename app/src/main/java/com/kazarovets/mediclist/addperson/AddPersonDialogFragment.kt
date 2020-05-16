package com.kazarovets.mediclist.addperson

import android.content.Context
import android.os.Bundle
import android.view.View
import com.kazarovets.mediclist.base.fragment.BaseDialogVMFragment
import com.kazarovets.mediclist.R
import com.kazarovets.mediclist.activity.di.ActivityComponent
import com.kazarovets.mediclist.addperson.di.DaggerAddPersonComponent
import com.kazarovets.mediclist.extensions.dpToPx
import com.kazarovets.mediclist.extensions.getDeviceSize
import kotlinx.android.synthetic.main.add_person_dialog.*
import kotlin.math.roundToInt

class AddPersonDialogFragment : BaseDialogVMFragment<AddPersonViewModel>() {
    override fun getViewModelClass() = AddPersonViewModel::class


    override val layoutId: Int
        get() = R.layout.add_person_dialog

    override fun getDialogHeight(context: Context): Int {
        return (context.getDeviceSize().y * 0.7).roundToInt()
    }


    override val isDialogCancelable: Boolean
        get() = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        apCloseButton.setOnClickListener {
            closeDialog()
        }

        apNameEdit.onTextChanged = { updateAddButtonEnabled() }
        apCategorySelector.onCategorySelected = { updateAddButtonEnabled() }

        apButtonAdd.setOnClickListener {
            viewModel.onAddClicked(getScreenValues())
            closeDialog()
        }
    }

    private fun getScreenValues(): AddPersonScreenValues {
        return AddPersonScreenValues(
            apNameEdit.getText(),
            apCategorySelector.selectedCategory
        )
    }

    private fun updateAddButtonEnabled() {
        val values = getScreenValues()
        val isEnabled = values.name?.isNotEmpty() == true &&
                values.category != null
        apButtonAdd.isEnabled = isEnabled
    }

    override fun injectDependencies(activityComponent: ActivityComponent) {
        DaggerAddPersonComponent.builder()
            .activityComponent(activityComponent)
            .build()
            .inject(this)
    }
}