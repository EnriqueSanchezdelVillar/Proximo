package com.example.primo.proximoconecta.Volunteer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.primo.proximoconecta.MainActivity
import com.example.primo.proximoconecta.R
import kotlinx.android.synthetic.main.activity_home_volunteer.*

class HomeVolunteerActivity : MainActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_volunteer)
        bottom_navigation_view_v.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.profile_v -> {
                    val fragment = VolunProfileFragment.newInstance()
                    openFragment(fragment)
                    true
                }
                R.id.activities_v -> {
                    val fragment = ActivitiesVolunFragment.newInstance()
                    openFragment(fragment)
                    true
                }
                R.id.notifications_v -> {
                    val fragment = VolunNotificationFragment.newInstance()
                    openFragment(fragment)
                    true
                }

                else -> false
            }

        }

        val fragment = VolunProfileFragment.newInstance()
        openFragment(fragment)
        bottom_navigation_view_v.selectedItemId = R.id.fragment_profile_v

    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container_v, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}