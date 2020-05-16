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
        addPersonScreenValues: AddPersonScreenValues
    ) {
        val name = addPersonScreenValues.name ?: return
        val category = addPersonScreenValues.category ?: return
        val person = createPerson(name, category, false)
        personsRepository.addPerson(person)
    }

    private fun createPerson(
        name: String, category: CovidCategory,
        isClosed: Boolean
    ) = AppPerson(
        0,
        name,
        "+375(29)23124234", "ул. Птушкина, дом Колотушкина",
        listOf("12.12", "10.12"),
        disabilityCertificateDate = "14.12",
        treatment = "лечить осторожно",
        category = category,
        isClosed = isClosed,
        addedAt = System.currentTimeMillis(),
        updatedAt = System.currentTimeMillis()
    )
}