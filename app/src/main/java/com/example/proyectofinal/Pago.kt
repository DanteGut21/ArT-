package com.example.proyectofinal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import database.DatabaseHelper

class Pago : Fragment() {
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Initialize the database helper
        databaseHelper = DatabaseHelper(requireContext())

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_pago, container, false)

        val btnPayPal: Button = view.findViewById(R.id.btnPayPal)
        val btnBankTransfer: Button = view.findViewById(R.id.btnBankTransfer)
        val btnInStoreDeposit: Button = view.findViewById(R.id.btnInStoreDeposit)

        val userId = arguments?.getInt("userId") ?: -1 // Ensure you pass "userId" in the arguments

        btnPayPal.setOnClickListener {
            processPayment("PayPal", userId)
        }
        btnBankTransfer.setOnClickListener {
            processPayment("BankTransfer", userId)
        }
        btnInStoreDeposit.setOnClickListener {
            processPayment("InStoreDeposit", userId)
        }

        return view // Return the view after setting up the buttons
    }

    private fun processPayment(method: String, userId: Int) {
        if (userId != -1) {
            // Actualiza o crea una orden en la base de datos
            databaseHelper.updateCartAndOrdersStatus(userId, method)
            Toast.makeText(context, "Pago realizado con exito", Toast.LENGTH_SHORT).show()

            // Cierra el fragmento de pago y regresa al fragmento o actividad anterior
            parentFragmentManager.popBackStack()
        } else {
            // Muestra un error o alerta al usuario que no ha iniciado sesión correctamente
            Toast.makeText(
                context,
                "Usuario no identificado, por favor inicia sesión.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
