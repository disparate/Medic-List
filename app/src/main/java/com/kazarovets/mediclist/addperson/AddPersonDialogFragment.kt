package com.kazarovets.mediclist.addperson

import android.content.Context
import android.os.Bundle
import android.view.View
import com.kazarovets.mediclist.base.fragment.BaseDialogVMFragment
import com.kazarovets.mediclist.R
import com.kazarovets.mediclist.activity.di.ActivityComponent
import com.kazarovets.mediclist.addperson.di.DaggerAddPersonComponent
import com.kazarovets.mediclist.extensions.getDeviceSize
import kotlinx.android.synthetic.main.add_person_dialog.*
import kotlin.math.roundToInt

class AddPersonDialogFragment : BaseDialogVMFragment<AddPersonViewModel>() {
    override fun getViewModelClass() = AddPersonViewModel::class


    override val layoutId: Int
        get() = R.layout.add_person_dialog


    override val isDialogCancelable: Boolean
        get() = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        personDialogCloseButton.setOnClickListener {
            closeDialog()
        }

        personDialogTitle.setText(R.string.add_new_title)
        personDialogButton.setText(R.string.add_new_button_add)
        personDialogButton.isEnabled = false

        personDialogNameEdit.onTextChanged = { updateAddButtonEnabled() }
        personDialogCategorySelector.onCategorySelected = { updateAddButtonEnabled() }

        personDialogButton.setOnClickListener {
            viewModel.onAddClicked(getScreenValues())
            closeDialog()
        }
    }

    private fun getScreenValues(): AddPersonScreenValues {
        return AddPersonScreenValues(
            personDialogNameEdit.getText(),
            personDialogCategorySelector.selectedCategory
        )
    }

    private fun updateAddButtonEnabled() {
        val values = getScreenValues()
        val isEnabled = values.name?.isNotEmpty() == true &&
                values.category != null
        personDialogButton.isEnabled = isEnabled
    }

    override fun injectDependencies(activityComponent: ActivityComponent) {
        DaggerAddPersonComponent.builder()
            .activityComponent(activityComponent)
            .build()
            .inject(this)
    }
}