package com.example.primo.proximoconecta

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

open class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * Sobreescribimos el botón de atrás
     */
    override fun onBackPressed() {
        // Si hay fragments en el stack (pila)
        if (supportFragmentManager.backStackEntryCount > 0) {
            // volver atras a nivel fragment
            supportFragmentManager.popBackStackImmediate()
        } else {
            // si no, volver atras a nivel activity
            super.onBackPressed()
        }
    }

}
