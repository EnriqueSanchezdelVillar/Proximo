package com.example.primo.proximoconecta.Volunteer


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.primo.proximoconecta.NavigationFragment.BaseFragment

import com.example.primo.proximoconecta.R


class VolunNotificationFragment : BaseFragment() {

    companion object {
        fun newInstance(): VolunNotificationFragment = VolunNotificationFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_volun_notification, container, false)
    }


}
