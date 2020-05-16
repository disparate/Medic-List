package com.kazarovets.mediclist.category

import com.kazarovets.mediclist.category.bo.CategoryUIPerson
import com.kazarovets.mediclist.category.bo.TabCategory
import com.kazarovets.mediclist.persons.bo.AppPerson
import javax.inject.Inject

class CategoryPersonMapper @Inject constructor() {

    fun mapPerson(person: AppPerson, category: TabCategory): CategoryUIPerson {
        return CategoryUIPerson(
            id = person.personId,
            name = person.personName,
            phone = person.phoneNumber,
            category = person.category,
            tabCategory = category
        )
    }
}