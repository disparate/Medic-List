package com.kazarovets.mediclist.category

import com.kazarovets.mediclist.category.bo.CategoryUIPerson
import com.kazarovets.mediclist.category.bo.TabCategory
import com.kazarovets.mediclist.persons.bo.AppPerson
import javax.inject.Inject

class CategoryPersonMapper @Inject constructor() {

    fun mapPerson(person: AppPerson, category: TabCategory): CategoryUIPerson {
        return CategoryUIPerson(
            name = person.personName,
            id = person.personId,
            phone = person.phoneNumber,
            category = person.category,
            tabCategory = category
        )
    }
}