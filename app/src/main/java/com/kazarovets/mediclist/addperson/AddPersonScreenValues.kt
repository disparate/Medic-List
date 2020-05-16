package com.kazarovets.mediclist.addperson

import com.kazarovets.mediclist.category.bo.CovidCategory

data class AddPersonScreenValues (
    val name: String?,
    val category: CovidCategory?
)