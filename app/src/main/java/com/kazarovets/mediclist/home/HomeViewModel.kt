package com.kazarovets.mediclist.home

import androidx.lifecycle.MutableLiveData
import com.kazarovets.mediclist.app.navigation.AppRouter
import com.kazarovets.mediclist.app.navigation.Screens
import com.kazarovets.mediclist.base.vm.BaseViewModel
import com.kazarovets.mediclist.category.bo.TabCategory
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val appRouter: AppRouter
) : BaseViewModel() {

    val tabs = MutableLiveData<List<TabCategory>>().apply {
        value = TabCategory.values().toList()
    }

    fun onAddPersonClick() {
        appRouter.navigateTo(Screens.addPerson())
    }
}