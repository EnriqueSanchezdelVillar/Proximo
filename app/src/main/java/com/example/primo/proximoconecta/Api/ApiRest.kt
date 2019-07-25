package com.example.primo.proximoconecta.Api

import com.example.primo.proximoconecta.Activities.Activity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiRest {//​
    lateinit var service: ApiService
    lateinit var actividad: Activity

    //​
    val URL = "https://proximo-eoi.herokuapp.com/"
    //​​
    fun initService() {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(ApiService::class.java)
    }
}