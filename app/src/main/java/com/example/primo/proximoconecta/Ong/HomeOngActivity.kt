package com.example.primo.proximoconecta.Ong

import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.primo.proximoconecta.MainActivity
import com.example.primo.proximoconecta.R
import kotlinx.android.synthetic.main.activity_home_ong.*

class HomeOngActivity : MainActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_ong)

        bottom_navigation_view_o.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.profile_o -> {
                    val fragment = ProfileFragment.newInstance()
                    openFragment(fragment)
                    true
                }
                R.id.activities_o -> {
                    val fragment = ListActivityFragment.newInstance()
                    openFragment(fragment)
                    true
                }
                R.id.notifications_o -> {
                    val fragment = NotificationFragment.newInstance()
                    openFragment(fragment)
                    true
                }
                R.id.newact -> {
                    val fragment = NewActivityFragment.newInstance()
                    openFragment(fragment)
                    true
                }

                else -> false
            }

        }

        val fragment = ProfileFragment.newInstance()
        openFragment(fragment)
        bottom_navigation_view_o.selectedItemId = R.id.fragment_profile_o

    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container_o, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}