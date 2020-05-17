package com.kazarovets.mediclist.persons.repo.database.bo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.kazarovets.mediclist.category.bo.CovidCategory
import com.kazarovets.mediclist.persons.repo.database.PersonTypeConverters

@Entity
class DBPerson(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "phoneNumber")
    var phoneNumber: String?,

    @ColumnInfo(name = "address")
    var address: String?,

    @ColumnInfo(name = "smears")
    val smearsDates: List<String>,

    @ColumnInfo(name = "disabilityCertificateDate")
    val disabilityCertificateDate: String?,

    @ColumnInfo(name = "treatment")
    val treatment: String?,

    @ColumnInfo(name = "covidCategory")
    val category: CovidCategory,

    @ColumnInfo(name = "isClosed")
    val isClosed: Boolean,

    @ColumnInfo(name = "additionalNotes")
    val additionalNotes: String,

    @ColumnInfo(name = "addedAt")
    val addedAt: Long,

    @ColumnInfo(name = "updatedAt")
    val updatedAt: Long
)