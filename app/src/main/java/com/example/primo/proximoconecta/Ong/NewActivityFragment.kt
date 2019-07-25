package com.example.primo.proximoconecta.Ong


import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.primo.proximoconecta.Activities.Activity
import com.example.primo.proximoconecta.Api.ApiRest
import com.example.primo.proximoconecta.NavigationFragment.BaseFragment

import com.example.primo.proximoconecta.R
import kotlinx.android.synthetic.main.fragment_new_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewActivityFragment : BaseFragment() {

    companion object {
        fun newInstance(): NewActivityFragment = NewActivityFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_activity, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ApiRest.initService()

        btnCreate.setOnClickListener {
            createActivity(this)
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.main_container_o, DoneFragment(), "Done")?.addToBackStack(null)?.commit()

        }

        // Recycler

    }



    private fun createActivity(fragment: NewActivityFragment) {

        val prefs= context!!.getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE)
        val authorization = prefs.getString("token", null)
        val userId= prefs.getString("userId",null)

        Log.v(TAG, "Valores:" + etdate.text.toString() + "adress"+  etaddress.text.toString() + "descrip"+ etdescription.text.toString() +
                "titulo" + ettitle.text.toString() + "auth: "+ authorization + "  id: "+ userId)

        val call = ApiRest.service.createActivity(Activity(address=etaddress.text.toString(), description = etdescription.text.toString(),
            date= etdate.text.toString(),
            title= ettitle.text.toString(),userId=userId) ,"Bearer $authorization")

        call.enqueue(object : Callback<Activity> {

            override fun onResponse(call: Call<Activity>, response: Response<Activity>) { //​
                val body = response.body()//​
                Log.v(TAG, "Success: " + body.toString())
                if (response.isSuccessful && body != null){//​
                    // Guardar en local token y role

                    // Lanza pantalla nueva
                    Log.v(TAG, "va bien owner papa")
                } else {
                    Log.e(TAG, "Success Error2.")
                }//​
            }//​
            override fun onFailure(call: Call<Activity>, t: Throwable) {
                Log.e(TAG, "Error: " + t.message)
            }
        })
    }

}
