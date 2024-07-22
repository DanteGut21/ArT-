package com.example.proyectofinal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal.model.CarritoItem

class Carrito : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CarritoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_carrito, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Creando datos de muestra
        val sampleData = listOf(
            CarritoItem(
                id = 1,
                name = "Té de manzanilla",
                price = 150.00,
                quantity = 2,
                imageUrl = R.drawable.te
            ),
            CarritoItem(
                id = 2,
                name = "Té verde",
                price = 120.00,
                quantity = 3,
                imageUrl = R.drawable.te
            ),  // Asegúrate de que estos drawables existan en tu carpeta drawable
            CarritoItem(
                id = 3,
                name = "Té negro",
                price = 100.00,
                quantity = 1,
                imageUrl = R.drawable.te
            )
        )

        adapter = CarritoAdapter(sampleData)  // Utiliza la lista de sampleData
        recyclerView.adapter = adapter
        return view
    }
}//Class Carrito