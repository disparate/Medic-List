package com.kazarovets.mediclist.smearstable

import androidx.lifecycle.MutableLiveData
import com.kazarovets.mediclist.app.navigation.AppRouter
import com.kazarovets.mediclist.base.vm.BaseViewModel
import com.kazarovets.mediclist.extensions.observeOnUI
import com.kazarovets.mediclist.persons.bo.AppPerson
import com.kazarovets.mediclist.persons.repo.PersonsRepository
import com.kazarovets.mediclist.smearstable.adapter.SmearsTableElement
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class SmearsTableViewModel @Inject constructor(
    private val router: AppRouter,
    private val personsRepository: PersonsRepository
) : BaseViewModel() {

    val smearsTable = MutableLiveData<List<SmearsTableElement>>()

    init {
        personsRepository.getPersonsOnceAndStream()
            .map { it.convertToSmearsElements() }
            .observeOnUI()
            .subscribeBy(
                onNext = {
                    smearsTable.value = it
                }
            )
            .addTo(compositeDisposable)
    }

    fun onClose() {
        router.exit()
    }

    fun List<AppPerson>.convertToSmearsElements(): List<SmearsTableElement> {
        return this
            .filter { it.isClosed.not() }
            .sortedByDescending { it.updatedAt }
            .mapNotNull {
            if (it.smearsDates.isEmpty()) {
                null
            } else {
                SmearsTableElement(it.personName, it.smearsDates)
            }
        }
    }
}