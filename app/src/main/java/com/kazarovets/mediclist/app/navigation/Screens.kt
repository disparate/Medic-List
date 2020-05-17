package com.kazarovets.mediclist.app.navigation

import androidx.fragment.app.Fragment
import com.kazarovets.mediclist.addperson.AddPersonFragment
import com.kazarovets.mediclist.changeperson.ChangePersonFragment
import com.kazarovets.mediclist.home.HomeFragment

object Screens {

    fun home() = fromFragment(HomeFragment())

    fun addPerson() = fromFragment(AddPersonFragment())

    fun changePerson(id: Int) = fromFragment(ChangePersonFragment.newInstance(id))

    private fun fromFragment(fragment: Fragment): AppScreen {
        return object : AppScreen() {
            override fun getFragment(): Fragment {
                return fragment
            }
        }
    }

    private fun fromFragment(fragment: Fragment, customKey: String): AppScreen {
        return object : AppScreen() {
            override fun getFragment(): Fragment {
                return fragment
            }

            override fun getScreenKey(): String {
                return ROUTER_SCREEN_SHOP_KEY
            }
        }
    }

    val ROUTER_SCREEN_SHOP_KEY = "shop_screen"
}