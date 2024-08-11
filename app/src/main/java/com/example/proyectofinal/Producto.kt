package com.example.proyectofinal

import android.content.Context
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
import database.DatabaseHelper

class Producto : Fragment() {
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_producto, container, false)
        databaseHelper = DatabaseHelper(requireContext())

        try {
            arguments?.let { bundle ->
                val productId = bundle.getInt("productId")
                val product =
                    databaseHelper.getProduct(productId) // Método para obtener un producto por ID

                product?.let {
                    view.findViewById<TextView>(R.id.productName).text = it.name
                    view.findViewById<TextView>(R.id.productDescription).text = it.description
                    view.findViewById<TextView>(R.id.productCategory).text = it.category
                    view.findViewById<TextView>(R.id.productPrice).text =
                        getString(R.string.price_format, it.price)
                    val imageResId =
                        resources.getIdentifier(it.imageResId, "drawable", context?.packageName)
                    view.findViewById<ImageView>(R.id.productImage).setImageResource(imageResId)
                }
            }
        } catch (e: Exception) {
            Toast.makeText(
                context,
                "Error al abrir detalles del producto: ${e.localizedMessage}",
                Toast.LENGTH_LONG
            ).show()
        }

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
            if (isUserLoggedIn()) {
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
    }//OnCreate

    private fun isUserLoggedIn(): Boolean {
        val sharedPreferences = activity?.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)
        return sharedPreferences?.getBoolean("isLoggedIn", false) ?: false
    }
}//Class Producto