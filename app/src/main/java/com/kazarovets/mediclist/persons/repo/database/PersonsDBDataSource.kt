package com.kazarovets.mediclist.persons.repo.database

import com.kazarovets.mediclist.extensions.mapList
import com.kazarovets.mediclist.persons.bo.AppPerson
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonsDBDataSource @Inject constructor(
    private val dao: PersonsDao,
    private val personsDBMapper: PersonsDBMapper
) {

    fun getPersonsOnceAndStream(): Observable<List<AppPerson>> {
        return dao.getPersons()
            .mapList { personsDBMapper.mapPersonFromDB(it) }
            .subscribeOn(Schedulers.io())
    }

    fun getPerson(id: Int): Single<AppPerson> {
        return dao.getPerson(id)
            .map {
                personsDBMapper.mapPersonFromDB(it)
            }
            .subscribeOn(Schedulers.io())
    }

    fun addNewPerson(person: AppPerson) {
        scheduleInBg { dao.insertOrUpdatePerson(personsDBMapper.mapPersonToDB(person)) }
    }

    fun updatePerson(person: AppPerson) {
        scheduleInBg { dao.insertOrUpdatePerson(personsDBMapper.mapPersonToDB(person)) }
    }

    fun deletePerson(person: AppPerson) {
        scheduleInBg { dao.deletePerson(personsDBMapper.mapPersonToDB(person)) }
    }

    private fun scheduleInBg(func: () -> Unit) {
        Schedulers.io().scheduleDirect { func() }
    }
}