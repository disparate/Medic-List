package com.kazarovets.mediclist.addperson

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.kazarovets.mediclist.base.fragment.BaseDialogVMFragment
import com.kazarovets.mediclist.R
import com.kazarovets.mediclist.activity.di.ActivityComponent
import com.kazarovets.mediclist.addperson.di.DaggerAddPersonComponent
import com.kazarovets.mediclist.base.vm.BaseViewModel
import kotlinx.android.synthetic.main.add_person_dialog.*

abstract class BasePersonDialogFragment<VM : BaseViewModel> : BaseDialogVMFragment<VM>() {

    override val layoutId: Int
        get() = R.layout.add_person_dialog

    override val isDialogCancelable: Boolean
        get() = false

    abstract fun onButtonClicked()

    abstract val titleRes: Int

    abstract val buttonRes: Int

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        personDialogCloseButton.setOnClickListener {
            closeDialog()
        }

        personDialogTitle.setText(titleRes)
        personDialogButton.setText(buttonRes)

        personDialogNameEdit.onTextChanged = { updateButtonEnabled() }
        personDialogCategorySelector.onCategorySelected = { updateButtonEnabled() }
        personDialogPhone.onTextChanged = { updateButtonEnabled() }


        personDialogButton.setOnClickListener {
            onButtonClicked()
            closeDialog()
        }
    }

    protected fun getScreenValues(): PersonScreenValues {
        return PersonScreenValues(
            name = personDialogNameEdit.getText(),
            category = personDialogCategorySelector.selectedCategory,
            phone = personDialogPhone.getText(),
            address = personDialogAddress.getText(),
            isClosed = if (personDialogIsClosedCheckbox.isVisible) {
                personDialogIsClosedCheckbox.isChecked
            } else null,
            smears = personDialogSmears.getSmears()
        )
    }

    private fun updateButtonEnabled() {
        val values = getScreenValues()
        val isEnabled = values.name?.isNotEmpty() == true &&
                values.category != null &&
                values.phone?.isNotEmpty() == true
        personDialogButton.isEnabled = isEnabled
    }

}

class AddPersonDialogFragment : BasePersonDialogFragment<AddPersonViewModel>() {

    override fun getViewModelClass() = AddPersonViewModel::class

    override fun onButtonClicked() {
        viewModel.onAddClicked(getScreenValues())
    }

    override val titleRes: Int = R.string.add_new_title

    override val buttonRes: Int = R.string.add_new_button_add

    override fun injectDependencies(activityComponent: ActivityComponent) {
        DaggerAddPersonComponent.builder()
            .activityComponent(activityComponent)
            .build()
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        personDialogButton.isEnabled = false

        personDialogIsClosedCheckbox.isVisible = false
    }
}