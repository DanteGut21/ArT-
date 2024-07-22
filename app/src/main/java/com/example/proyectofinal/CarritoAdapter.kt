package com.example.proyectofinal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal.model.CarritoItem

class CarritoAdapter(private var items: List<CarritoItem>) :
    RecyclerView.Adapter<CarritoAdapter.CartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_carrito, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class CartViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imgTe: ImageView = view.findViewById(R.id.imgvTeCarrito)
        private val title: TextView = view.findViewById(R.id.tvTituloTe)
        private val price: TextView = view.findViewById(R.id.tvPrecioTe)
        private val quantity: TextView = view.findViewById(R.id.quantity)
        private val increaseButton: Button = view.findViewById(R.id.increment)
        private val decreaseButton: Button = view.findViewById(R.id.decrement)

        fun bind(item: CarritoItem) {
            title.text = item.name
            price.text = "\$${item.price}"
            quantity.text = item.quantity.toString()
            imgTe.setImageResource(item.imageUrl)


            increaseButton.setOnClickListener {
                // Incrementar lógica aquí
            }

            decreaseButton.setOnClickListener {
                // Decrementar lógica aquí
            }
        }
    }
}
