package com.example.primo.proximoconecta.NavigationFragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.MenuItem
import android.view.View
import com.example.primo.proximoconecta.MainActivity

abstract class BaseFragment : Fragment() {

    lateinit var mainActivity: MainActivity

    /**
     * Cuando se ha creado la vista
     * Instanciamos el activity padre
     * Habilitamos el botón de atrás
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainActivity = activity as MainActivity
        mainActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setHasOptionsMenu(true)

    }


    /**
     * Cuando se ha pulsado algun botón del toolbar
     *
     * En caso de que se pulse android.R.id.home -> volver atrás
     * si no, llamamos al "super" que es lo mismo que no hacer nada
     * y dejar que el fragment trabaje como siempre.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                mainActivity.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}
