package com.example.proyectofinal

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class Usuario : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_usuario, container, false)
        val btnEnvio: Button = view.findViewById(R.id.btnEnvio)
        val btnfactura: Button = view.findViewById(R.id.btnfactura)

        btnEnvio.setOnClickListener {
            // Aquí manejarías el pago por transferencia bancaria
            Toast.makeText(context, "Envios...", Toast.LENGTH_SHORT).show()
//            val intent = Intent(activity, PagoProducto::class.java)
//            startActivity(intent)

            val envios = Envios()
            val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, envios)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        btnfactura.setOnClickListener({
            showAlertDialog()
        })
        return view
    }//OnCreateView

    private fun showAlertDialog() {
        val builder =
            AlertDialog.Builder(requireContext()) // use requireContext() to get the context
        builder.setTitle("Facturación")
        builder.setMessage("Para emitir su factura favor de mandar correo a la siguiente dirección \n factura@arte.com")
        builder.setPositiveButton("Aceptar") { dialog, which ->
            Toast.makeText(context, "Aceptado", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("Cancelar") { dialog, which ->
            dialog.dismiss()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

}//Class usuario