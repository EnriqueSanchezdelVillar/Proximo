package com.example.primo.proximoconecta.Register


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.primo.proximoconecta.Activities.UserData
import com.example.primo.proximoconecta.Api.ApiRest
import com.example.primo.proximoconecta.Login.Login
import com.example.primo.proximoconecta.Ong.HomeOngActivity

import com.example.primo.proximoconecta.R
import kotlinx.android.synthetic.main.fragment_register_ong.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterOngFragment : Fragment() {

    companion object {
        fun newInstance(): RegisterOngFragment = RegisterOngFragment()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_ong, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ApiRest.initService()//​
        btnRegisterOng.setOnClickListener {
            signUp()
        }
    }//​
    private fun signUp() {//​
        val call = ApiRest.service.signUp(Register(firstName = etOngName.text.toString(),email =  etOngmail.text.toString(),
            password = etOngPass.text.toString(),role = "ROLE_OWNER"))
        call.enqueue(object : Callback<Login> {
            override fun onResponse(call: Call<Login>, response: Response<Login>) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    val prefs = context?.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE)
                    val editor = prefs?.edit()
                    editor?.putString("token", body.access_token)
                    editor?.putString("role", body.role)
                    editor?.apply()

                    getUserData()

                    val intent = Intent(context, HomeOngActivity::class.java)
                    startActivity(intent)
                } else {
                    Log.e("SignUp", response.errorBody()?.string())
                }
            }//​
            override fun onFailure(call: Call<Login>, t: Throwable) {
                Log.e("signUp", t.message)
            }
        })
    }

    private fun getUserData() {//​

        val prefs= context?.getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE)
        val authorization = prefs?.getString("token", null)

        val call = ApiRest.service.getUserData("Bearer $authorization")
        call.enqueue(object : Callback<UserData> {
            override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    val prefs = context?.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE)
                    val editor = prefs?.edit()
                    editor?.putString("userId", body._id)

                    editor?.apply()



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