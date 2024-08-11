package com.example.proyectofinal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal.model.CartItem

class CarritoAdapter(private var items: MutableList<CartItem>) :
    RecyclerView.Adapter<CarritoAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productName: TextView = view.findViewById(R.id.tvTituloTe)
        val productPrice: TextView = view.findViewById(R.id.tvPrecioTe)
        val productImage: ImageView = view.findViewById(R.id.imgvTeCarrito)
        val quantityText: TextView = view.findViewById(R.id.quantity)
        val decrementButton: Button = view.findViewById(R.id.decrement)
        val incrementButton: Button = view.findViewById(R.id.increment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_carrito, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.productName.text = item.name
        holder.productPrice.text = "${item.price}"
        holder.quantityText.text = item.quantity.toString()
        holder.productImage.setImageResource(
            holder.itemView.context.resources.getIdentifier(
                item.imageResId ?: "default_image", "drawable", holder.itemView.context.packageName
            )
        )

        holder.decrementButton.setOnClickListener {
            if (item.quantity > 1) {
                item.quantity -= 1
                holder.quantityText.text = item.quantity.toString()
                // Actualizar base de datos o lógica de negocio aquí
            }
        }

        holder.incrementButton.setOnClickListener {
            item.quantity += 1
            holder.quantityText.text = item.quantity.toString()
            // Actualizar base de datos o lógica de negocio aquí
        }
    }

    override fun getItemCount() = items.size

    fun clearItems() {
        items.clear()
        notifyDataSetChanged()
    }

    // Añadir o quitar items del carrito
    fun updateItems(newItems: List<CartItem>) {
        items = newItems.toMutableList()
        notifyDataSetChanged()
    }

    // Método para actualizar la cantidad de un producto en el carrito
    fun updateQuantity(position: Int, quantity: Int) {
        if (position < items.size) {
            items[position].quantity = quantity
            notifyItemChanged(position)
        }
    }
}
