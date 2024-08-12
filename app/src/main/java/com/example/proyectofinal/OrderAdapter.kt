package com.example.proyectofinal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal.model.Order

class OrderAdapter(private val orders: List<Order>) :
    RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    class OrderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewId: TextView = view.findViewById(R.id.textViewId)
        val textViewFecha: TextView = view.findViewById(R.id.textViewFecha)
        val textViewEstado: TextView = view.findViewById(R.id.textViewEstado)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.order_item, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orders[position]
        holder.textViewId.text =
            "Order ID: ${order.id}, Cliente ID: ${order.idCliente}, Carrito ID: ${order.idCarrito}"
        holder.textViewFecha.text = "Fecha: ${order.fecha}"
        holder.textViewEstado.text = "Estado: ${order.estado}"
    }

    override fun getItemCount(): Int = orders.size
}
