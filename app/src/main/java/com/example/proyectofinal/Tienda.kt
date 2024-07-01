package com.example.proyectofinal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class Tienda : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_tienda, container, false)

        // Configura el clic en el LinearLayout "ItemTienda"
        val itemTienda: LinearLayout = view.findViewById(R.id.ItemTienda)
        itemTienda.setOnClickListener {
            // Transacción de fragmentos
            val producto = Producto()
            val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, producto)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        return view
    }//onCreateView
}//Class Tienda
