package com.example.proyectofinal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

data class Product(
    val id: Int,
    val name: String,
    val shortDescription: String?,
    val longDescription: String?,
    val price: Double,
    val stock: Int,
    val imageResId: Int
)

class Tienda : Fragment() {

    private fun getProducts(): List<Product> {
        return listOf(
            Product(
                1,
                "Dragon Ball",
                "Té Floreciente",
                "Como equilibrista en la cuerda floja, aparece un crisantemo rosa " +
                        "suspendido en un arco de jazmines. Una flor de caléndula espera ansiosa " +
                        "recibirlo por si no puede evitar caer. Cada perla está formada por hojas de" +
                        " Té atadas a mano con algodón, con flores naturales ocultas en su interior.",
                100.0,
                10,
                R.drawable.dragonball
            ),
            Product(
                2,
                "Frutos rojos",
                "Infusión de frutos rojos",
                "Descripción larga del Producto 2.",
                55.0,
                5,
                R.drawable.frojos
            ),
            Product(
                3,
                "Matcha",
                "Té verde matcha",
                "Descripción larga del matcha.",
                200.0,
                5,
                R.drawable.matcha
            ),
            Product(
                4,
                "Té Negro Arabic",
                "Té negro Arabic 1001 Noches",
                "Descripción larga del té negro.",
                400.0,
                5,
                R.drawable.mentabolsa
            ),
            Product(
                5,
                "Mango y Vainilla",
                "Infusión de mango y vainilla",
                "Descripción larga de la infusión.",
                350.0,
                5,
                R.drawable.mango
            ),
            Product(
                6,
                "Té Negro Arabic",
                "Té negro Arabic 1001 Noches",
                "Descripción larga del té negro.",
                400.0,
                5,
                R.drawable.mentabolsa
            ),
            Product(
                7,
                "Té Negro Arabic",
                "Té negro Arabic 1001 Noches",
                "Descripción larga del té negro.",
                400.0,
                5,
                R.drawable.mentabolsa
            ),
            Product(
                8,
                "Té Negro Arabic",
                "Té negro Arabic 1001 Noches",
                "Descripción larga del té negro.",
                400.0,
                5,
                R.drawable.mentabolsa
            ),
            Product(
                9,
                "Té Negro Arabic",
                "Té negro Arabic 1001 Noches",
                "Descripción larga del té negro.",
                400.0,
                5,
                R.drawable.mentabolsa
            ),
            Product(
                10,
                "Té Negro Arabic",
                "Té negro Arabic 1001 Noches",
                "Descripción larga del té negro.",
                400.0,
                5,
                R.drawable.mentabolsa
            ),
            Product(
                11,
                "Té Negro Arabic",
                "Té negro Arabic 1001 Noches",
                "Descripción larga del té negro.",
                400.0,
                5,
                R.drawable.mentabolsa
            ),
            Product(
                12,
                "Té Negro Arabic",
                "Té negro Arabic 1001 Noches",
                "Descripción larga del té negro.",
                400.0,
                5,
                R.drawable.mentabolsa
            ),
            Product(
                13,
                "Té Negro Arabic",
                "Té negro Arabic 1001 Noches",
                "Descripción larga del té negro.",
                400.0,
                5,
                R.drawable.mentabolsa
            ),
            Product(
                14,
                "Té Negro Arabic",
                "Té negro Arabic 1001 Noches",
                "Descripción larga del té negro.",
                400.0,
                5,
                R.drawable.mentabolsa
            ),
            Product(
                15,
                "Té Negro Arabic",
                "Té negro Arabic 1001 Noches",
                "Descripción larga del té negro.",
                400.0,
                5,
                R.drawable.mentabolsa
            )
        )
    }

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
