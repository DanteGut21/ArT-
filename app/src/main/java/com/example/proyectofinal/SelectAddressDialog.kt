package com.example.proyectofinal

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal.model.Direccion
import database.DatabaseHelper

class SelectAddressDialog : DialogFragment() {
    private lateinit var listener: OnAddressSelectedListener
    private lateinit var databaseHelper: DatabaseHelper
    private var userId: Int = -1

    interface OnAddressSelectedListener {
        fun onAddressSelected(direccion: Direccion)
    }

    fun setOnAddressSelectedListener(listener: OnAddressSelectedListener) {
        this.listener = listener
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        databaseHelper = DatabaseHelper(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userId = it.getInt("userId", -1)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = activity?.layoutInflater?.inflate(R.layout.fragment_select_address_dialog, null)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerViewAddress)
        recyclerView?.layoutManager = LinearLayoutManager(context)

        val addresses = databaseHelper.getAddresses(userId)
        if (addresses.isEmpty()) {
            Toast.makeText(
                context,
                "No hay direcciones guardadas. Agrega una desde tu perfil.",
                Toast.LENGTH_LONG
            ).show()
        }

        val adapter = AddressAdapter(addresses) { selectedAddress ->
            listener.onAddressSelected(selectedAddress)
            dismiss()
        }
        recyclerView?.adapter = adapter

        return AlertDialog.Builder(requireContext())
            .setTitle("Selecciona una dirección")
            .setView(view)
            .setNegativeButton("Cancelar") { _, _ -> dismiss() }
            .create()
    }

    companion object {
        fun newInstance(userId: Int): SelectAddressDialog {
            val fragment = SelectAddressDialog().apply {
                arguments = Bundle().apply {
                    putInt("userId", userId)
                }
            }
            return fragment
        }
    }
}
