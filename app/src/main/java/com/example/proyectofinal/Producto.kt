package com.example.proyectofinal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

class Producto : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_producto, container, false)
        val btnPago: Button = view.findViewById(R.id.btnPago)
        val btnCarrito: Button = view.findViewById(R.id.btnCarrito)
        btnPago.setOnClickListener {
            Toast.makeText(context, "Preparando opciones de pago...", Toast.LENGTH_SHORT).show()

            // Creando una instancia del fragmento de pago
            val pagoFragment = Pago()

            // Realizando la transacción para añadir el fragmento de pago sobre el fragmento actual
            parentFragmentManager.beginTransaction()
                .add(R.id.fragment_container, pagoFragment)
                .addToBackStack(null) // Permite al usuario volver al fragmento anterior
                .commit()
        }
        btnCarrito.setOnClickListener {
            Toast.makeText(context, "Carrito", Toast.LENGTH_SHORT).show()
//            val intent = Intent(this, Carrito::class.java)
//            startActivity(intent)
        }
        return view
    }//OnCreateView
}//Class Producto
