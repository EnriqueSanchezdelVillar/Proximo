package com.example.primo.proximoconecta.Login

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.primo.proximoconecta.Activities.UserData
import com.example.primo.proximoconecta.Api.ApiRest
import com.example.primo.proximoconecta.MainActivity
import com.example.primo.proximoconecta.Ong.HomeOngActivity
import com.example.primo.proximoconecta.R
import com.example.primo.proximoconecta.Volunteer.HomeVolunteerActivity
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : MainActivity() {

    val TAG = "MiApp"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ApiRest.initService()//​
        // Comprobamos si existe un token
        val prefs = applicationContext.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE)
        val token = prefs.getString("token", null)//​
        Log.v(TAG, "El token es, $token")
       /* if(token != null) {
            // Lanza pantalla nueva
            val intent = Intent(applicationContext, HomeVolunteerActivity::class.java)
            startActivity(intent)
            this.finish() // cierra la pantalla actual
        }//​*/
        btnLogin.setOnClickListener {
            callLogin()
        }
    }//​
    private fun callLogin() {
        val call = ApiRest.service.login(UserLogin(email.text.toString(), password.text.toString()))
        call.enqueue(object : Callback<Login> {
            override fun onResponse(call: Call<Login>, response: Response<Login>) { //​
                val body = response.body()//​
                Log.v(TAG, "Success: " + body.toString())
                if (response.isSuccessful && body != null && body.role=="ROLE_OWNER"){//​
                    // Guardar en local token y role
                    val prefs = applicationContext.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE)
                    val editor = prefs.edit()
                    editor.putString("token", body.access_token)
                    editor.putString("role", body.role)
                    editor.apply()//​

                    getUserData()
                    // Lanza pantalla nueva
                    val intent = Intent(applicationContext, HomeOngActivity::class.java)
                    startActivity(intent)//​
                }
                else if(response.isSuccessful && body != null && body.role=="ROLE_CUSTOMER") {

                    val prefs = applicationContext.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE)
                    val editor = prefs.edit()
                    editor.putString("token", body.access_token)
                    editor.putString("role", body.role)
                    editor.apply()//​
                    getUserData()

                    // Lanza pantalla nueva
                    val intent = Intent(applicationContext, HomeVolunteerActivity::class.java)
                    startActivity(intent)//​

                }
                else {
                    Log.e(TAG, "Success Error.")
                }//​
            }//​
            override fun onFailure(call: Call<Login>, t: Throwable) {
                Log.e(TAG, "Error: " + t.message)
            }
        })
    }

    private fun getUserData() {//​

        val prefs= this!!.getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE)
        val access_token = prefs.getString("token", null)

        val call = ApiRest.service.getUserData("Bearer $access_token")
        call.enqueue(object : Callback<UserData> {
            override fun onResponse(call: Call<UserData>, response: Response<UserData>) {

                val body = response.body()
                if (response.isSuccessful && body != null) {

                    val prefs = applicationContext.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE)
                    val editor = prefs.edit()
                    editor?.putString("userId", body._id)

                    editor?.apply()

                } else {
                    Log.e("getUserData1", response.errorBody()?.string())
                }
            }//​
            override fun onFailure(call: Call<UserData>, t: Throwable) {
                Log.e("getUserData2", t.message)
            }
        })
    }

}

