package com.example.primo.proximoconecta.Volunteer


import android.content.Context
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.primo.proximoconecta.Activities.Activity
import com.example.primo.proximoconecta.Activities.UserActivity
import com.example.primo.proximoconecta.Api.ApiRest
import com.example.primo.proximoconecta.NavigationFragment.BaseFragment

import com.example.primo.proximoconecta.R
import kotlinx.android.synthetic.main.fragment_complet_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CompletActivityFragment : BaseFragment() {


    var data: ArrayList<Activity> = ArrayList()
    val TAG = "MiAppaa"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_complet_activity, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ApiRest.initService()
        getOneActivity()


        btnactivitySingIn.setOnClickListener {

            Log.v("KELOKE", "vamos viendo")
            createUserActivity()

            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.main_container_v, DoneSingFragment(), "Done")?.addToBackStack(null)?.commit()


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

                    titleActivity?.text = body.title
                    date_activity?.text = body.date
                    ubication_activity?.text = body.address
                    description_activity?.text = body.description

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

    private fun createUserActivity() {

        val TAG = "MiAp245"
        val prefs = context!!.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE)
        val authorization = prefs.getString("token", null)
        val activityId=prefs.getString("activityId", null)
        val userId=prefs.getString("userId", null)

        Log.i(TAG," user   "+ userId + "    "+activityId )

        val call = ApiRest.service.createUserActivity(UserActivity(userId = userId,  activityId =activityId ),"Bearer $authorization")

        call.enqueue(object : Callback<UserActivity> {
            override fun onResponse(call: Call<UserActivity>, response: Response<UserActivity>) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    Log.i(TAG, body.toString())

                }
                else {
                    Log.e(TAG, response.errorBody()?.string())
                }
            }

            override fun onFailure(call: Call<UserActivity>, t: Throwable) {
                Log.e(TAG, t.message)
            }
        })
    }
}

