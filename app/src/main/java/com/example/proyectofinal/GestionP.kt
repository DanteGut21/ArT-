package com.example.proyectofinal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import database.DatabaseHelper

class GestionP : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: OrderAdapter
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_gestion_p, container, false)
        recyclerView = view.findViewById(R.id.ordersRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        databaseHelper = DatabaseHelper(requireContext())
        loadOrders()

        return view
    }

    private fun loadOrders() {
        val orders =
            databaseHelper.getAllOrders() // Suponiendo que esta función devuelve una lista de órdenes
        adapter = OrderAdapter(orders)
        recyclerView.adapter = adapter
    }
}//GestionP