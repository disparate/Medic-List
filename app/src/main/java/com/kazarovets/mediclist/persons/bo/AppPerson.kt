package com.kazarovets.mediclist.persons.bo

import com.kazarovets.mediclist.category.bo.CovidCategory

data class AppPerson(
    val personId: Int,
    val personName: String,
    val phoneNumber: String?,
    val address: String?,
    val smearsDates: List<String>,
    val disabilityCertificateDate: String?,
    val treatment: String?,
    val category: CovidCategory,
    val isClosed: Boolean,
    val additionaNotes: String,
    val addedAt: Long,
    val updatedAt: Long
)