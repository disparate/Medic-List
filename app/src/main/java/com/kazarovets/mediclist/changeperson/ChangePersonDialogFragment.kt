package com.kazarovets.mediclist.changeperson

import android.os.Bundle
import android.view.View
import com.kazarovets.mediclist.R
import com.kazarovets.mediclist.activity.di.ActivityComponent
import com.kazarovets.mediclist.addperson.BasePersonDialogFragment
import com.kazarovets.mediclist.addperson.PersonScreenValues
import com.kazarovets.mediclist.base.fragment.BaseDialogVMFragment
import com.kazarovets.mediclist.changeperson.di.DaggerChangePersonComponent
import com.kazarovets.mediclist.extensions.withArguments
import kotlinx.android.synthetic.main.add_person_dialog.*

class ChangePersonDialogFragment : BasePersonDialogFragment<ChangePersonViewModel>() {

    override fun getViewModelClass() = ChangePersonViewModel::class

    override fun onButtonClicked() {
        viewModel.onChangeClicked(getScreenValues())
    }

    override val titleRes: Int = R.string.change_person_title
    override val buttonRes: Int = R.string.change_person_button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userId = arguments?.getInt(ARG_USER_ID, -1) ?: return
        if (userId == -1) return
        val initScreenValues = viewModel.init(userId) ?: return

        fillWithScreenValues(initScreenValues)
    }

    private fun fillWithScreenValues(values: PersonScreenValues) {
        personDialogNameEdit.setText(values.name.orEmpty())
        values.category?.let { personDialogCategorySelector.selectCategory(it) }
        personDialogAddress.setText(values.address.orEmpty())
        personDialogPhone.setText(values.phone.orEmpty())
        personDialogIsClosedCheckbox.isChecked = values.isClosed ?: false
        personDialogSmears.setSmears(values.smears)
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