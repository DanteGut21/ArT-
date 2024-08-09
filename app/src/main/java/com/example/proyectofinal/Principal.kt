package com.example.proyectofinal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class Principal : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_principal, container, false)

//        // Inicializa el botón dentro del fragmento
//        val BTNAgregar: Button = view.findViewById(R.id.BTNAgregar)
//
//        // Establece el listener del botón para abrir la actividad Agregar
//        BTNAgregar.setOnClickListener {
//            // Necesitas usar requireActivity() para obtener el contexto de la actividad que contiene el fragmento
//            val intent = Intent(requireActivity(), Agregar::class.java)
//            startActivity(intent)
//        }

        return view
    }//onCreateView
}//Class Principal