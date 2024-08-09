package com.example.proyectofinal
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
            val imageResId = bundle.getInt("productImageResId")

            view.findViewById<TextView>(R.id.productName).text = name
            view.findViewById<TextView>(R.id.productDescription).text = description
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
            Toast.makeText(context, "Carrito", Toast.LENGTH_SHORT).show()
        }//btnCarrito
        return view
    }//onCreateView
}//Class Producto