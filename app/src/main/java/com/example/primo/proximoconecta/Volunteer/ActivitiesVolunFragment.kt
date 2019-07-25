package com.example.primo.proximoconecta.Volunteer


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.primo.proximoconecta.Activities.Activity
import com.example.primo.proximoconecta.Adapters.AdapterActivities
import com.example.primo.proximoconecta.Api.ApiRest
import com.example.primo.proximoconecta.NavigationFragment.BaseFragment

import com.example.primo.proximoconecta.R
import kotlinx.android.synthetic.main.fragment_activities_volun.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ActivitiesVolunFragment : BaseFragment() {

    companion object {
        fun newInstance(): ActivitiesVolunFragment = ActivitiesVolunFragment()
    }

    var data: ArrayList<Activity> = ArrayList()
    private var adapter: AdapterActivities? = null
    val TAG = "MiApp"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activities_volun, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ApiRest.initService()
        getActivity(this)


        // Recycler
        val mLayoutManager = GridLayoutManager(context, 1)
        recycler_title_v.layoutManager = mLayoutManager

        adapter = AdapterActivities(data)
        recycler_title_v.adapter = adapter

        adapter?.setOnClick {

            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.main_container_v,
                CompletActivityFragment(), "redFragment"
            )?.addToBackStack("recipeFragment")?.commit()

            val prefs = context!!.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE)
            val editor = prefs.edit()
            editor?.putString("activityId",it._id)
            editor?.apply()

        }


    }


    private fun getActivity(fragment: ActivitiesVolunFragment) {

        val prefs = context!!.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE)
        val authorization = prefs.getString("token", null)

        val call = ApiRest.service.getActivity("Bearer $authorization")

        call.enqueue(object : Callback<List<Activity>> {
            override fun onResponse(call: Call<List<Activity>> , response: Response<List<Activity>>) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    Log.i(TAG, body.toString())

                    data.addAll(body)
                    adapter?.notifyDataSetChanged()

                }
                else {
                    Log.e(TAG, response.errorBody()?.string())
                }
            }

            override fun onFailure(call: Call<List<Activity>>, t: Throwable) {
                Log.e(TAG, t.message)
            }
        })
    }

}


