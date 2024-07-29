package com.example.proyectofinal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

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
            // Aquí manejarías el pago por transferencia bancaria
            Toast.makeText(context, "Pagos...", Toast.LENGTH_SHORT).show()
//            val intent = Intent(activity, PagoProducto::class.java)
//            startActivity(intent)

            val pago = Pago()
            val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, pago)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        btnCarrito.setOnClickListener {
            Toast.makeText(context, "Carrito", Toast.LENGTH_SHORT).show()
//            val intent = Intent(this, Carrito::class.java)
//            startActivity(intent)
        }
        return view
    }//OnCreateView
}//Class Producto
