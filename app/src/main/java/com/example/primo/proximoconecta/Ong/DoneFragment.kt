package com.example.primo.proximoconecta.Ong


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.primo.proximoconecta.R
import kotlinx.android.synthetic.main.fragment_done.*


class DoneFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_done, container, false)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnback.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.main_container_o, NewActivityFragment(), "New")?.addToBackStack(null)?.commit()

        }
    }


}
