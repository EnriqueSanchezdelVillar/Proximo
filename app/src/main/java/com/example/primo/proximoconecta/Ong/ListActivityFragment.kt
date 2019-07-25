package com.example.primo.proximoconecta.Ong


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.primo.proximoconecta.Activities.Activity
import com.example.primo.proximoconecta.Adapters.AdapterActivityOng
import com.example.primo.proximoconecta.Api.ApiRest
import com.example.primo.proximoconecta.NavigationFragment.BaseFragment

import com.example.primo.proximoconecta.R
import kotlinx.android.synthetic.main.fragment_list_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ListActivityFragment : BaseFragment() {

    companion object {
        fun newInstance(): ListActivityFragment = ListActivityFragment()
    }

    var data: ArrayList<Activity> = ArrayList()
    private var adapter: AdapterActivityOng? = null
    val TAG = "MiApp"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_activity, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ApiRest.initService()
        getActivityOng(this)


        // Recycler
        val mLayoutManager = GridLayoutManager(context, 1)
        recycler_acti_o.layoutManager = mLayoutManager

        adapter = AdapterActivityOng(data)
        recycler_acti_o.adapter = adapter

        adapter?.setOnClick {

            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.main_container_v,
                SingleActOngFragment(), "redFragment"
            )?.addToBackStack("recipeFragment")?.commit()

            val prefs = context!!.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE)
            val editor = prefs.edit()
            editor?.putString("activityId",it._id)
            editor?.apply()

        }

    }


    private fun getActivityOng(fragment: ListActivityFragment) {

        val prefs = context!!.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE)
        val authorization = prefs.getString("token", null)
        val userId= prefs.getString("userId",null)


        val call = ApiRest.service.getActivityOng(userId,"Bearer $authorization")

        call.enqueue(object : Callback<List<Activity>> {
            override fun onResponse(call: Call<List<Activity>>, response: Response<List<Activity>>) {
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



