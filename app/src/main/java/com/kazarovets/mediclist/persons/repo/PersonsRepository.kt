package com.kazarovets.mediclist.persons.repo

import com.kazarovets.mediclist.category.bo.CovidCategory
import com.kazarovets.mediclist.persons.bo.AppPerson
import com.kazarovets.mediclist.persons.repo.database.PersonsDBDataSource
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonsRepository @Inject constructor(
    private val dbDataSource: PersonsDBDataSource
) {

    fun getPersonsOnceAndStream(): Observable<List<AppPerson>> {
        return dbDataSource.getPersonsOnceAndStream()
            .subscribeOn(Schedulers.io())
    }

    fun addPerson(person: AppPerson) {
        return dbDataSource.addNewPerson(person)
    }
}