package com.example.proyectofinal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class Tienda : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tienda, container, false)
        val gridLayout: GridLayout = view.findViewById(R.id.gridLayoutProductos)
        val productos = getProducts()

        for (producto in productos) {
            val itemView = inflater.inflate(R.layout.item_producto, gridLayout, false)
            val imageView: ImageView = itemView.findViewById(R.id.imageProducto)
            val textView: TextView = itemView.findViewById(R.id.textProductoNombre)

            imageView.setImageResource(producto.imageResId)
            textView.text = producto.name

            itemView.setOnClickListener {
                openProductDetails(producto)
            }

            gridLayout.addView(itemView)
        }

        return view
    }

    private fun openProductDetails(producto: Product) {
        val productoFragment = Producto().apply {
            arguments = Bundle().apply {
                putInt("productId", producto.id)
                putString("productName", producto.name)
                putString("productDescription", producto.longDescription)
                putDouble("productPrice", producto.price)
                putString("productCategory", producto.category)
                putInt("productStock", producto.stock)
                putInt("productImageResId", producto.imageResId)
            }
        }
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, productoFragment)
            .addToBackStack(null)
            .commit()
    }
}