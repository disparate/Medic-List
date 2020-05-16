package com.kazarovets.mediclist.persons.repo.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kazarovets.mediclist.persons.repo.database.bo.DBPerson

@Database(entities = [DBPerson::class], version = 1)
@TypeConverters(PersonTypeConverters::class)
abstract class PersonsDatabase : RoomDatabase() {
    abstract fun personsDao(): PersonsDao
}