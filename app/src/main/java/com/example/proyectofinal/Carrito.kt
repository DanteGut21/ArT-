package com.example.proyectofinal

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal.model.Direccion
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
                databaseHelper.cancelCartAndCreateOrder(userId)
                adapter.clearItems()
                Toast.makeText(
                    context,
                    "Carrito cancelado y orden registrada como cancelada",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // Botón para finalizar la compra
        view.findViewById<Button>(R.id.btnPPago).setOnClickListener {
            val sharedPreferences =
                activity?.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)
            val isUserLoggedIn = sharedPreferences?.getBoolean("isLoggedIn", false) ?: false

            if (isUserLoggedIn) {
                val userId = sharedPreferences?.getInt("userId", -1) ?: -1
                if (userId != -1) {
                    try {
                        val addresses = databaseHelper.getAddresses(userId)
                        if (addresses.isNotEmpty()) {
                            val selectAddressDialog = SelectAddressDialog.newInstance(userId)
                            selectAddressDialog.setOnAddressSelectedListener(object :
                                SelectAddressDialog.OnAddressSelectedListener {
                                override fun onAddressSelected(direccion: Direccion) {
                                    // Actualiza el estado del carrito como "Procesado" o similar
                                    databaseHelper.updateCartAndOrdersStatus(userId, "Completa")
                                    // Refrescar los ítems del carrito
                                    refreshCartItems()
                                    // Aquí puedes iniciar el fragmento de pago
                                    openPaymentFragment()
                                }
                            })
                            selectAddressDialog.show(parentFragmentManager, "SelectAddressDialog")
                        } else {
                            Toast.makeText(
                                context,
                                "No tienes direcciones guardadas. Por favor, agrega una dirección.",
                                Toast.LENGTH_SHORT
                            ).show()
                            // Ejemplo de cómo crear y mostrar DireccionFragment desde otra parte (e.g., Producto)
                            val direccionFragment = DireccionFragment.newInstance(userId)
                            parentFragmentManager.beginTransaction()
                                .replace(R.id.fragment_container, direccionFragment)
                                .addToBackStack(null)
                                .commit()
                        }
                    } catch (e: Exception) {
                        Toast.makeText(
                            context,
                            "Error al procesar el pago: ${e.message}",
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
        }
    }//btnPPago

    private fun openPaymentFragment() {
        val pagoFragment = Pago().apply {
            arguments = Bundle().apply {
                putInt("userId", userId)
            }
        }
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, pagoFragment)
            .addToBackStack(null)
            .commit()
    }


    private fun refreshCartItems() {
        val sharedPreferences = activity?.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)
        val userId = sharedPreferences?.getInt("userId", -1) ?: -1
        if (userId != -1) {
            val productsInCart = databaseHelper.getCartProducts(userId)
            (recyclerView.adapter as? CarritoAdapter)?.updateItems(productsInCart)
        }
    }

}//Class carrito
