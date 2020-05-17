package com.kazarovets.mediclist.category

import androidx.lifecycle.MutableLiveData
import com.kazarovets.mediclist.app.navigation.AppRouter
import com.kazarovets.mediclist.app.navigation.Screens
import com.kazarovets.mediclist.base.vm.BaseViewModel
import com.kazarovets.mediclist.category.bo.CategoryUIPerson
import com.kazarovets.mediclist.category.bo.CovidCategory
import com.kazarovets.mediclist.category.bo.TabCategory
import com.kazarovets.mediclist.extensions.mapList
import com.kazarovets.mediclist.extensions.observeOnUI
import com.kazarovets.mediclist.persons.bo.AppPerson
import com.kazarovets.mediclist.persons.repo.PersonsRepository
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

class CategoryViewModel @Inject constructor(
    private val personsRepository: PersonsRepository,
    private val mapper: CategoryPersonMapper,
    private val appRouter: AppRouter
) : BaseViewModel() {

    val categoryPersons = MutableLiveData<List<CategoryUIPerson>>()

    fun init(category: TabCategory) {
        observePersons(category)
    }

    fun openChangePerson(person: CategoryUIPerson) {
        appRouter.navigateTo(Screens.changePerson(person.id))
    }

    private fun observePersons(category: TabCategory) {
        personsRepository.getPersonsOnceAndStream()
            .map { it.filterForCategory(category) }
            .mapList { mapper.mapPerson(it, category) }
            .observeOnUI()
            .subscribeBy(
                onError = {
                    Timber.e("error getting persons")
                },
                onNext = {
                    categoryPersons.value = it
                }
            ).addTo(compositeDisposable)
    }

    private fun List<AppPerson>.filterForCategory(tabCategory: TabCategory): List<AppPerson> {
        return when (tabCategory) {
            TabCategory.COVID -> filterForCovidCategory(CovidCategory.COVID)
            TabCategory.COVID_PNEUMONIA -> filterForCovidCategory(CovidCategory.COVID_PNEUMONIA)
            TabCategory.LEVEL1 -> filterForCovidCategory(CovidCategory.LEVEL1)
            TabCategory.LEVEL2 -> filterForCovidCategory(CovidCategory.LEVEL2)
            TabCategory.PROBABLE -> filterForCovidCategory(CovidCategory.PROBABLE)
            TabCategory.CLOSED -> this.filter { it.isClosed }
        }
    }

    private fun List<AppPerson>.filterForCovidCategory(covidCategory: CovidCategory): List<AppPerson> {
        return this.filter {
            it.category == covidCategory && it.isClosed.not()
        }
    }
}