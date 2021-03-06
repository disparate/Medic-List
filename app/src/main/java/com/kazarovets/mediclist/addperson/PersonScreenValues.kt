package com.kazarovets.mediclist.addperson

import com.kazarovets.mediclist.category.bo.CovidCategory

data class PersonScreenValues(
    val name: String?,
    val category: CovidCategory?,
    val phone: String?,
    val address: String?,
    val isClosed: Boolean?,
    val smears: List<String>,
    val disabilityCertificate: String?,
    val treatment: String?,
    val additionalNotes: String?
)