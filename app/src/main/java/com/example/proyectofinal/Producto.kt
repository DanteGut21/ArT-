package com.example.proyectofinal

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class Producto : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_producto, container, false)

        arguments?.let { bundle ->
            val name = bundle.getString("productName")
            val description = bundle.getString("productDescription")
            val category = bundle.getString("productCategory")
            val price = bundle.getDouble("productPrice", 0.0)
            val imageResId = bundle.getInt("productImageResId")

            view.findViewById<TextView>(R.id.productName).text = name
            view.findViewById<TextView>(R.id.productDescription).text = description
            view.findViewById<TextView>(R.id.productCategory).text = category
            view.findViewById<TextView>(R.id.productPrice).text =
                getString(R.string.price_format, price)
            view.findViewById<ImageView>(R.id.productImage).setImageResource(imageResId)
        }//arguments

        val btnPago: Button = view.findViewById(R.id.btnPago)
        val btnCarrito: Button = view.findViewById(R.id.btnCarrito)
        btnPago.setOnClickListener {
            Toast.makeText(context, "Preparando opciones de pago...", Toast.LENGTH_SHORT).show()
            val pagoFragment = Pago()
            parentFragmentManager.beginTransaction()
                .add(R.id.fragment_container, pagoFragment)
                .addToBackStack(null)
                .commit()
        }//btnPago
        btnCarrito.setOnClickListener {
            if ((activity as MainActivity).checkSession()) {
                Toast.makeText(context, "Producto añadido al carrito", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    context,
                    "Por favor, inicia sesión para continuar.",
                    Toast.LENGTH_SHORT
                ).show()
                startActivity(Intent(activity, Login::class.java))
            }
        }//btnCarrito
        return view
    }//onCreateView
}//Class Producto
