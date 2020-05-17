package com.kazarovets.mediclist.addperson

import com.kazarovets.mediclist.app.navigation.AppRouter
import com.kazarovets.mediclist.base.vm.BaseViewModel
import com.kazarovets.mediclist.category.bo.CovidCategory
import com.kazarovets.mediclist.persons.bo.AppPerson
import com.kazarovets.mediclist.persons.repo.PersonsRepository
import javax.inject.Inject

class AddPersonViewModel @Inject constructor(
    private val personsRepository: PersonsRepository,
    private val appRouter: AppRouter
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
            disabilityCertificateDate = addPersonScreenValues.disabilityCertificate,
            treatment = addPersonScreenValues.treatment,
            category = addPersonScreenValues.category ?: return,
            isClosed = false,
            additionaNotes = "", //TODO
            addedAt = System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis()
        )
        personsRepository.addPerson(person)
    }


    fun closeScreen() {
        appRouter.exit()
    }
}