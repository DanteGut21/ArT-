package com.example.proyectofinal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal.model.Item
import com.example.proyectofinal.model.Order

class GestionP : Fragment() {

    private var ordersRecyclerView: RecyclerView? = null
    private lateinit var orderAdapter: OrderAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_gestion_p, container, false)
        setupRecyclerView(view)
        return view
    }//onCreateView

    private fun setupRecyclerView(view: View) {
        ordersRecyclerView = view.findViewById(R.id.ordersRecyclerView)
        ordersRecyclerView?.layoutManager = LinearLayoutManager(context)

        // Datos de ejemplo utilizando las definiciones correctas de las clases Item y Order
        val sampleData = listOf(
            Order(
                userName = "Usuario1",
                items = listOf(
                    Item(name = "Producto1", quantity = 2),
                    Item(name = "Producto2", quantity = 3)
                ),
                status = "pagado"
            ),
            Order(
                userName = "Usuario2",
                items = listOf(
                    Item(name = "Producto3", quantity = 1)
                ),
                status = "pago pendiente"
            ),
            Order(
                userName = "Usuario3",
                items = listOf(
                    Item(name = "Producto4", quantity = 1)
                ),
                status = "cancelado"
            )
        )

        orderAdapter = OrderAdapter(sampleData)
        ordersRecyclerView?.adapter = orderAdapter
    }//setupRecyclerView
}//GestionP