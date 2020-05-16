package com.kazarovets.mediclist.changeperson

import android.os.Bundle
import android.view.View
import com.kazarovets.mediclist.R
import com.kazarovets.mediclist.activity.di.ActivityComponent
import com.kazarovets.mediclist.addperson.AddPersonScreenValues
import com.kazarovets.mediclist.base.fragment.BaseDialogVMFragment
import com.kazarovets.mediclist.changeperson.di.DaggerChangePersonComponent
import com.kazarovets.mediclist.extensions.withArguments
import kotlinx.android.synthetic.main.add_person_dialog.*
import kotlin.reflect.KClass

class ChangePersonDialogFragment : BaseDialogVMFragment<ChangePersonViewModel>() {

    override fun getViewModelClass() = ChangePersonViewModel::class

    override val layoutId: Int
        get() = R.layout.add_person_dialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userId = arguments?.getInt(ARG_USER_ID, -1) ?: return
        if (userId == -1) return
        val initScreenValues = viewModel.init(userId) ?: return

        fillWithScreenValues(initScreenValues)

        personDialogCloseButton.setOnClickListener {
            closeDialog()
        }

        personDialogTitle.setText(R.string.change_person_title)
        personDialogButton.setText(R.string.change_person_button)

        personDialogNameEdit.onTextChanged = { updateButtonEnabled() }
        personDialogCategorySelector.onCategorySelected = { updateButtonEnabled() }

        personDialogButton.setOnClickListener {
            viewModel.onChangeClicked(getScreenValues())
            closeDialog()
        }

    }

    private fun fillWithScreenValues(values: AddPersonScreenValues) {
        personDialogNameEdit.setText(values.name.orEmpty())
        values.category?.let { personDialogCategorySelector.selectCategory(it) }
    }

    private fun getScreenValues(): AddPersonScreenValues {
        return AddPersonScreenValues(
            personDialogNameEdit.getText(),
            personDialogCategorySelector.selectedCategory
        )
    }

    private fun updateButtonEnabled() {
        val values = getScreenValues()
        val isEnabled = values.name?.isNotEmpty() == true &&
                values.category != null
        personDialogButton.isEnabled = isEnabled
    }

    override fun injectDependencies(activityComponent: ActivityComponent) {
        DaggerChangePersonComponent.builder()
            .activityComponent(activityComponent)
            .build()
            .inject(this)
    }

    companion object {

        private const val ARG_USER_ID = "arg_user_id"

        fun newInstance(userId: Int) = ChangePersonDialogFragment().withArguments {
            putInt(ARG_USER_ID, userId)
        }
    }
}