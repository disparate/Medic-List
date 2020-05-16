package com.kazarovets.mediclist.persons.repo.database

import androidx.room.*
import com.kazarovets.mediclist.persons.repo.database.bo.DBPerson
import io.reactivex.Observable

@Dao
interface PersonsDao {

    @Query("SELECT * FROM DBPerson")
    fun getPersons(): Observable<List<DBPerson>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdatePerson(person: DBPerson)

    @Delete
    fun deletePerson(person: DBPerson)

}