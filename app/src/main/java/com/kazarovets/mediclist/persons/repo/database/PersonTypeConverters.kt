package com.kazarovets.mediclist.persons.repo.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kazarovets.mediclist.category.bo.CovidCategory


object PersonTypeConverters {


    private val gson = Gson()
    @TypeConverter
    @JvmStatic
    fun restoreList(listOfString: String?): List<String> {
        return gson.fromJson(listOfString.orEmpty(), object : TypeToken<List<String>>() {}.type);
    }

    @TypeConverter
    @JvmStatic
    fun saveList(listOfString: List<String>?): String {
        return gson.toJson(listOfString.orEmpty());
    }


    private val CATEGORY_COVID = "COVID"
    private val CATEGORY_LEVEL1 = "level1"
    private val CATEGORY_LEVEL2 = "level2"
    private val CATEGORY_PROBABLE = "probable"

    @TypeConverter
    @JvmStatic
    fun restoreCategory(c: String?): CovidCategory? {
        return when (c) {
            CATEGORY_COVID -> CovidCategory.COVID
            CATEGORY_LEVEL1 -> CovidCategory.LEVEL1
            CATEGORY_LEVEL2 -> CovidCategory.LEVEL2
            CATEGORY_PROBABLE -> CovidCategory.PROBABLE
            else -> null
        }
    }

    @TypeConverter
    @JvmStatic
    fun saveCategory(c: CovidCategory?): String? {
        return when (c) {
            CovidCategory.COVID -> CATEGORY_COVID
            CovidCategory.LEVEL1 -> CATEGORY_LEVEL1
            CovidCategory.LEVEL2 -> CATEGORY_LEVEL2
            CovidCategory.PROBABLE -> CATEGORY_PROBABLE
            null -> null
        }
    }

}