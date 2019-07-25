package com.example.primo.proximoconecta.Adapters

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.primo.proximoconecta.Activities.Activity
import com.example.primo.proximoconecta.Api.ApiRest
import com.example.primo.proximoconecta.R
import kotlinx.android.synthetic.main.simple_activity.view.*

class AdapterActivityOng (private val data: ArrayList<Activity>) : RecyclerView.Adapter<AdapterActivityOng.ViewHolder>() {

    val TAG = "Pic"
    private lateinit var onClick: (actividades: Activity)->Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.simple_activity, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setOnClick(onClick: (item: Activity) ->Unit){
        this.onClick=onClick
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Activity)
        {
            Log.v(TAG,"date" + itemView.btndate.text )

            itemView.btnActivity.text=item.title

            itemView.btndate.text=item.address

            itemView.gotoActi.setOnClickListener {

                Log.v("click", "click")

                ApiRest.actividad =item
                Log.v("data", item._id)
                onClick(ApiRest.actividad)

            }

        }


    }
}