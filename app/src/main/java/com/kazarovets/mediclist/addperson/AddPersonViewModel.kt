package com.kazarovets.mediclist.addperson

import com.kazarovets.mediclist.base.vm.BaseViewModel
import com.kazarovets.mediclist.category.bo.CovidCategory
import com.kazarovets.mediclist.persons.bo.AppPerson
import com.kazarovets.mediclist.persons.repo.PersonsRepository
import javax.inject.Inject

class AddPersonViewModel @Inject constructor(
    private val personsRepository: PersonsRepository
) : BaseViewModel() {


    fun onAddClicked(
        addPersonScreenValues: PersonScreenValues
    ) {
        val person = AppPerson(
            0,
            personName = addPersonScreenValues.name ?: return,
            phoneNumber = addPersonScreenValues.phone ?: return,
            address = addPersonScreenValues.address,
            smearsDates = addPersonScreenValues.smears,
            disabilityCertificateDate = "14.12", //TODO: add
            treatment = "лечить осторожно", //TODO: add
            category = addPersonScreenValues.category ?: return,
            isClosed = false,
            addedAt = System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis()
        )
        personsRepository.addPerson(person)
    }
}