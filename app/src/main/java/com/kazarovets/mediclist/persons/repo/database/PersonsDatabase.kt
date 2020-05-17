package com.kazarovets.mediclist.persons.repo.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kazarovets.mediclist.persons.repo.database.bo.DBPerson
import dev.matrix.roomigrant.GenerateRoomMigrations

@Database(entities = [DBPerson::class], version = 2)
@TypeConverters(PersonTypeConverters::class)
@GenerateRoomMigrations
abstract class PersonsDatabase : RoomDatabase() {
    abstract fun personsDao(): PersonsDao
}