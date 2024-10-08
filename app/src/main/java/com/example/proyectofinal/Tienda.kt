package com.example.proyectofinal

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.proyectofinal.model.Product
import database.DatabaseHelper

class Tienda : Fragment() {
    private lateinit var databaseHelper: DatabaseHelper

    override fun onAttach(context: Context) {
        super.onAttach(context)
        databaseHelper = DatabaseHelper(context)
    }//OnAttach

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tienda, container, false)
        val gridLayout: GridLayout = view.findViewById(R.id.gridLayoutProductos)

        try {
            val productos = databaseHelper.getAllProducts()
            productos.forEach { producto ->
                val itemView = inflater.inflate(R.layout.item_producto, gridLayout, false)
                val imageView: ImageView = itemView.findViewById(R.id.imageProducto)
                val textView: TextView = itemView.findViewById(R.id.textProductoNombre)
                val imageId =
                    resources.getIdentifier(producto.imageResId, "drawable", context?.packageName)

                imageView.setImageResource(imageId)
                textView.text = producto.name

                itemView.setOnClickListener {
                    openProductDetails(producto)
                }
                gridLayout.addView(itemView)
            }
        } catch (e: Exception) {
            Toast.makeText(context, "Error al cargar productos: ${e.message}", Toast.LENGTH_LONG)
                .show()
        }
        return view
    }//OnCreate

    private fun openProductDetails(producto: Product) {
        try {
            val productoFragment = Producto().apply {
                arguments = Bundle().apply {
                    putInt("productId", producto.id)
                    putString("productName", producto.name)
                    putString("productDescription", producto.description)
                    putDouble("productPrice", producto.price)
                    putString("productCategory", producto.category)
                    putInt("productStock", producto.stock)
                    putString("productImageResId", producto.imageResId)
                }
            }
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, productoFragment)
                .addToBackStack(null)
                .commit()
        } catch (e: Exception) {
            Toast.makeText(context, "Error: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
        }
    }//openProductDetails
}//Class Tienda
