package com.kazarovets.mediclist.changeperson

import com.kazarovets.mediclist.addperson.AddPersonScreenValues
import com.kazarovets.mediclist.base.vm.BaseViewModel
import com.kazarovets.mediclist.persons.bo.AppPerson
import com.kazarovets.mediclist.persons.repo.PersonsRepository
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

class ChangePersonViewModel @Inject constructor(
    private val repo: PersonsRepository
) : BaseViewModel() {

    private var person: AppPerson? = null

    fun init(userId: Int): AddPersonScreenValues? {
        val person = try {
            repo.getPerson(userId).blockingGet()
        } catch (e: Exception) {
            Timber.e("error getting user by id $userId")
            return null
        }

        this.person = person
        return AddPersonScreenValues(
            person.personName,
            person.category
        )
    }

    fun onChangeClicked(values: AddPersonScreenValues) {
        person?.let {
            val newPerson = it.copy(
                personName = values.name ?: it.personName,
                category = values.category ?: it.category
            )
            repo.updatePerson(newPerson)
        }
    }
}