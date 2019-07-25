package com.example.primo.proximoconecta.Register

import android.os.Bundle
import com.example.primo.proximoconecta.MainActivity
import com.example.primo.proximoconecta.R
import kotlinx.android.synthetic.main.activity_registrer.*

class RegistrerActivity : MainActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrer)

        btnvoluntario_register.setOnClickListener {

            supportFragmentManager.beginTransaction().replace(R.id.fragment_register,RegisterVolunFragment(),"Volun").addToBackStack("Volun").commit()

        }

            btnOng_register.setOnClickListener {

                supportFragmentManager.beginTransaction().replace(R.id.fragment_register,RegisterOngFragment(),"Ong").addToBackStack("Ong").commit()

            }


    }
}
