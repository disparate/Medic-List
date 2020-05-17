package com.kazarovets.mediclist.changeperson

import com.kazarovets.mediclist.addperson.PersonScreenValues
import com.kazarovets.mediclist.app.navigation.AppRouter
import com.kazarovets.mediclist.base.vm.BaseViewModel
import com.kazarovets.mediclist.persons.bo.AppPerson
import com.kazarovets.mediclist.persons.repo.PersonsRepository
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

class ChangePersonViewModel @Inject constructor(
    private val repo: PersonsRepository,
    private val appRouter: AppRouter
) : BaseViewModel() {

    private var person: AppPerson? = null

    fun init(userId: Int): PersonScreenValues? {
        val person = try {
            repo.getPerson(userId).blockingGet()
        } catch (e: Exception) {
            Timber.e("error getting user by id $userId")
            return null
        }

        this.person = person
        return PersonScreenValues(
            name = person.personName,
            category = person.category,
            phone = person.phoneNumber,
            address = person.address,
            isClosed = person.isClosed,
            smears = person.smearsDates,
            disabilityCertificate = person.disabilityCertificateDate,
            treatment = person.treatment
        )
    }

    fun onChangeClicked(values: PersonScreenValues) {
        person?.let {
            val newPerson = it.copy(
                personName = values.name ?: it.personName,
                category = values.category ?: it.category,
                phoneNumber = values.phone ?: it.phoneNumber,
                address = values.address ?: it.address,
                isClosed = values.isClosed ?: it.isClosed,
                smearsDates = values.smears.orEmpty(),
                disabilityCertificateDate = values.disabilityCertificate
                    ?: it.disabilityCertificateDate,
                treatment = values.treatment ?: it.treatment
            )
            repo.updatePerson(newPerson)
        }
    }

    fun closeScreen() {
        appRouter.exit()
    }
}