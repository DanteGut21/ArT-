package com.example.proyectofinal

//import android.content.ClipData.Item
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal.model.Item
import com.example.proyectofinal.model.Order

class Administracion : AppCompatActivity() {
    private lateinit var ordersRecyclerView: RecyclerView
    private lateinit var orderAdapter: OrderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_administracion)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        ordersRecyclerView = findViewById(R.id.ordersRecyclerView)
        ordersRecyclerView.layoutManager = LinearLayoutManager(this)

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
        ordersRecyclerView.adapter = orderAdapter
    }//setupRecyclerView

}//Class Administracion