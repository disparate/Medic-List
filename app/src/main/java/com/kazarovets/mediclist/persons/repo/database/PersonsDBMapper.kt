package com.kazarovets.mediclist.persons.repo.database

import com.kazarovets.mediclist.persons.bo.AppPerson
import com.kazarovets.mediclist.persons.repo.database.bo.DBPerson
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonsDBMapper @Inject constructor() {

    fun mapPersonFromDB(person: DBPerson): AppPerson {
        return AppPerson(
            personId = person.id,
            personName = person.name,
            phoneNumber = person.phoneNumber,
            address = person.address,
            smearsDates = person.smearsDates,
            disabilityCertificateDate = person.disabilityCertificateDate,
            treatment = person.treatment,
            category = person.category,
            isClosed = person.isClosed,
            additionaNotes = person.additionalNotes,
            addedAt = person.addedAt,
            updatedAt = person.updatedAt
        )
    }

    fun mapPersonToDB(person: AppPerson): DBPerson {
        return DBPerson(
            id = person.personId,
            name = person.personName,
            phoneNumber = person.phoneNumber,
            address = person.address,
            smearsDates = person.smearsDates,
            disabilityCertificateDate = person.disabilityCertificateDate,
            treatment = person.treatment,
            category = person.category,
            isClosed = person.isClosed,
            additionalNotes = person.additionaNotes,
            addedAt = person.addedAt,
            updatedAt = person.updatedAt
        )
    }
}