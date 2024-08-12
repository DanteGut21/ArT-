package com.example.proyectofinal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal.model.Direccion

class AddressAdapter(
    private val addresses: List<Direccion>,
    private val onAddressSelected: (Direccion) -> Unit
) : RecyclerView.Adapter<AddressAdapter.AddressViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_direccion, parent, false)
        return AddressViewHolder(view)
    }

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        val address = addresses[position]
        holder.addressText.text = address.fullAddress()
        holder.itemView.setOnClickListener { onAddressSelected(address) }
    }

    override fun getItemCount(): Int = addresses.size

    class AddressViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val addressText: TextView = view.findViewById(R.id.textViewAddress)
    }
}
