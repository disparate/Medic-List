package com.kazarovets.mediclist.persons.di

import android.app.Application
import androidx.room.Room
import com.kazarovets.mediclist.persons.repo.database.PersonsDao
import com.kazarovets.mediclist.persons.repo.database.PersonsDatabase
import com.kazarovets.mediclist.persons.repo.database.PersonsDatabase_Migrations
import dagger.Module
import dagger.Provides

@Module
class PersonsDBModule {

    @Provides
    fun providePersonsDatabase(app: Application): PersonsDatabase {
        return Room.databaseBuilder(app, PersonsDatabase::class.java, "persons_db")
            .addMigrations(*PersonsDatabase_Migrations.build())
            .build()
    }

    @Provides
    fun providePersonsDao(db: PersonsDatabase): PersonsDao {
        return db.personsDao()
    }

}