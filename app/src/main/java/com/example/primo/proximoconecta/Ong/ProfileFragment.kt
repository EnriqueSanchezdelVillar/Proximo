package com.example.primo.proximoconecta.Ong


import android.content.Context
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.primo.proximoconecta.Activities.UserData
import com.example.primo.proximoconecta.Api.ApiRest
import com.example.primo.proximoconecta.NavigationFragment.BaseFragment

import com.example.primo.proximoconecta.R
import kotlinx.android.synthetic.main.fragment_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProfileFragment : BaseFragment() {

    companion object {
        fun newInstance(): ProfileFragment = ProfileFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ApiRest.initService()
        getUserData()

    }

    private fun getUserData() {//​

        val prefs= context!!.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE)
        val access_token = prefs.getString("token", null)

        val call = ApiRest.service.getUserData("Bearer $access_token")
        call.enqueue(object : Callback<UserData> {
            override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                val body = response.body()
                if (response.isSuccessful && body != null) {

                    tNameOng?.text = body.firstName
                    temailOng?.text = body.email

                } else {
                    Log.e("getUserData1", response.errorBody()?.string())
                }
            }//​
            override fun onFailure(call: Call<UserData>, t: Throwable) {
                Log.e("getUserData2", t.message)
            }
        })
    }


}