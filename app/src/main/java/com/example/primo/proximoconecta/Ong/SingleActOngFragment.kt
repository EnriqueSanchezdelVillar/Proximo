package com.example.primo.proximoconecta.Ong


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.primo.proximoconecta.Activities.Activity
import com.example.primo.proximoconecta.Api.ApiRest

import com.example.primo.proximoconecta.R
import kotlinx.android.synthetic.main.fragment_single_act_ong.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SingleActOngFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_single_act_ong, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ApiRest.initService()
        getOneActivity()


        btnactivitySeeAc.setOnClickListener {

            Log.v("KELOKE", "vamos viendo")


            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.main_container_v, ListActivityFragment(), "Done")?.addToBackStack(null)?.commit()


        }

    }


    private fun getOneActivity() {

        val TAG = "MiAp2323"
        val prefs = context!!.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE)
        val authorization = prefs.getString("token", null)
        val activityId=prefs.getString("activityId", null)



        val call = ApiRest.service.getOneActivity(activityId, "Bearer $authorization")

        call.enqueue(object : Callback<Activity> {
            override fun onResponse(call: Call<Activity>, response: Response<Activity>) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    Log.i(TAG, body.toString())

                    titleActivity_o?.text = body.title
                    date_activity_o?.text = body.date
                    ubication_activity_o?.text = body.address
                    description_activity_o?.text = body.description

                }
                else {
                    Log.e(TAG, response.errorBody()?.string())
                }
            }

            override fun onFailure(call: Call<Activity>, t: Throwable) {
                Log.e(TAG, t.message)
            }
        })
    }


}
