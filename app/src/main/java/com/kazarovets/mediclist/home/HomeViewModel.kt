package com.kazarovets.mediclist.home

import androidx.lifecycle.MutableLiveData
import com.kazarovets.mediclist.base.vm.BaseViewModel
import com.kazarovets.mediclist.category.bo.TabCategory
import javax.inject.Inject

class HomeViewModel @Inject constructor() : BaseViewModel() {

    val tabs = MutableLiveData<List<TabCategory>>().apply {
        value = TabCategory.values().toList()
    }
}