package com.example.primo.proximoconecta.StartScreen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.primo.proximoconecta.Login.LoginActivity
import com.example.primo.proximoconecta.MainActivity
import com.example.primo.proximoconecta.Ong.HomeOngActivity
import com.example.primo.proximoconecta.R
import com.example.primo.proximoconecta.Register.RegistrerActivity
import com.example.primo.proximoconecta.Volunteer.HomeVolunteerActivity
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        btnLogin.setOnClickListener {
            var Intent1 = Intent(this, LoginActivity::class.java)
            startActivity(Intent1)
        }

        btnRegister.setOnClickListener {
            var Intent2 = Intent(this, RegistrerActivity::class.java)
            startActivity(Intent2)
        }
    }
    override fun onResume() {
        super.onResume()
        intro.postDelayed({
            intro.visibility = View.GONE
            btnLogin.visibility = View.VISIBLE
            btnRegister.visibility = View.VISIBLE

        }, 2000)

    }

}