package com.example.proyectofinal

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import database.DatabaseHelper

class Carrito : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var databaseHelper: DatabaseHelper
    private var userId: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_carrito, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        databaseHelper = DatabaseHelper(requireContext())

        // Obtener el ID del usuario logueado desde SharedPreferences
        val sharedPreferences = activity?.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)
        userId = sharedPreferences?.getInt("userId", -1) ?: -1

        // Si hay un usuario logueado, cargar los productos en su carrito
        if (userId != -1) {
            val productsInCart = databaseHelper.getCartProducts(userId)
            val adapter = CarritoAdapter(productsInCart)
            recyclerView.adapter = adapter

            setupButtons(view, adapter)
        }

        return view
    }

    private fun setupButtons(view: View, adapter: CarritoAdapter) {
        // Botón para vaciar el carrito
        view.findViewById<Button>(R.id.btnPEliminar).setOnClickListener {
            if (userId != -1) {
                databaseHelper.updateCartAndOrdersStatus(userId, "Cancelado")
                adapter.clearItems()
                Toast.makeText(context, "Carrito vaciado con éxito", Toast.LENGTH_SHORT).show()
            }
        }

        // Botón para finalizar la compra
        view.findViewById<Button>(R.id.btnPPago).setOnClickListener {
            if (userId != -1) {
                databaseHelper.updateCartAndOrdersStatus(userId, "Completa")
                adapter.clearItems()
                Toast.makeText(context, "Compra finalizada con éxito", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
