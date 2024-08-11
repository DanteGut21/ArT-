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
            val sharedPrefs = activity?.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)
            val isUserLoggedIn = sharedPrefs?.getBoolean("isLoggedIn", false) ?: false

            if (isUserLoggedIn) {
                val userId = sharedPrefs?.getInt("userId", -1) ?: -1
                if (userId != -1) {
                    val productId = arguments?.getInt("productId") ?: -1
                    if (productId != -1) {
                        try {
                            val productStock = databaseHelper.getProductStock(productId)
                            if (productStock > 0) {
                                databaseHelper.addToCart(productId, userId, 1)
                                databaseHelper.updateProductStock(productId, -1)
                                Toast.makeText(
                                    context,
                                    "Producto añadido al carrito",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                Toast.makeText(
                                    context,
                                    "No hay suficiente stock disponible",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } catch (e: Exception) {
                            Toast.makeText(
                                context,
                                "Error al añadir al carrito: ${e.message}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            context,
                            "Error: ID de producto no encontrado",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } else {
                    Toast.makeText(context, "Error: ID de usuario no encontrado", Toast.LENGTH_LONG)
                        .show()
                }
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