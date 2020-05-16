package com.kazarovets.mediclist.app.di.vm

import com.kazarovets.mediclist.persons.repo.PersonsRepository

interface RepositoriesProvider {
    fun providePersons(): PersonsRepository
}