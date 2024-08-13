package com.example.proyectofinal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.proyectofinal.model.Direccion
import database.DatabaseHelper

class DireccionFragment : Fragment() {

    private lateinit var databaseHelper: DatabaseHelper
    private var userId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userId = it.getInt("userId")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_direccion, container, false)
        databaseHelper = DatabaseHelper(requireContext())

        val calleEditText = view.findViewById<EditText>(R.id.calleEditText)
        val numeroExteriorEditText = view.findViewById<EditText>(R.id.numeroExteriorEditText)
        val codigoPostalEditText = view.findViewById<EditText>(R.id.codigoPostalEditText)
        val estadoEditText = view.findViewById<EditText>(R.id.estadoEditText)
        val ciudadEditText = view.findViewById<EditText>(R.id.ciudadEditText)
        val coloniaEditText = view.findViewById<EditText>(R.id.coloniaEditText)
        val telefonoEditText = view.findViewById<EditText>(R.id.telefonoEditText)
        val saveButton = view.findViewById<Button>(R.id.saveButton)

        saveButton.setOnClickListener {
            val calle = calleEditText.text.toString()
            val numeroExterior = numeroExteriorEditText.text.toString()
            val codigoPostal = codigoPostalEditText.text.toString()
            val estado = estadoEditText.text.toString()
            val ciudad = ciudadEditText.text.toString()
            val colonia = coloniaEditText.text.toString()
            val telefono = telefonoEditText.text.toString()

            if (calle.isEmpty() || numeroExterior.isEmpty() || codigoPostal.isEmpty() ||
                estado.isEmpty() || ciudad.isEmpty() || colonia.isEmpty() || telefono.isEmpty()
            ) {
                Toast.makeText(context, "Por favor, completa todos los campos", Toast.LENGTH_SHORT)
                    .show()
            } else {
                userId?.let {
                    try {
                        val direccion = Direccion(
                            id = 0,  // ID no es necesario para la inserción, SQLite lo genera automáticamente
                            calle = calle,
                            numeroExterior = numeroExterior,
                            codigoPostal = codigoPostal,
                            estado = estado,
                            ciudad = ciudad,
                            colonia = colonia,
                            telefono = telefono,
                            idCliente = it  // Pasa el userID aquí como idCliente
                        )
                        val newAddressId = databaseHelper.addDireccion(direccion)
                        if (newAddressId != -1L) {
                            Toast.makeText(
                                context,
                                "Dirección guardada con éxito",
                                Toast.LENGTH_SHORT
                            ).show()
                            parentFragmentManager.popBackStack()
                        } else {
                            Toast.makeText(
                                context,
                                "Error al guardar la dirección",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } catch (e: Exception) {
                        Toast.makeText(
                            context,
                            "Error al guardar la dirección: ${e.message}",
                            Toast.LENGTH_LONG
                        ).show()
                        e.printStackTrace()
                    }
                } ?: Toast.makeText(context, "Usuario no identificado", Toast.LENGTH_SHORT).show()

            }
        }

        return view
    }

    companion object {
        fun newInstance(userId: Int): DireccionFragment {
            val fragment = DireccionFragment()
            val args = Bundle()
            args.putInt("userId", userId)
            fragment.arguments = args
            return fragment
        }
    }
}