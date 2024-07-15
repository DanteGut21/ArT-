package com.example.proyectofinal

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PagoProducto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pago_producto)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }

        val btnBankTransfer: Button = findViewById(R.id.btnBankTransfer)
        val btnPayPal: Button = findViewById(R.id.btnPayPal)
        val btnInStoreDeposit: Button = findViewById(R.id.btnInStoreDeposit)

        btnBankTransfer.setOnClickListener {
            // Aquí manejarías el pago por transferencia bancaria
            Toast.makeText(this, "Transferencia Bancaria", Toast.LENGTH_SHORT).show()
        }

        btnPayPal.setOnClickListener {
            // Aquí manejarías el pago por PayPal
            Toast.makeText(this, "PayPal", Toast.LENGTH_SHORT).show()
        }

        btnInStoreDeposit.setOnClickListener {
            // Aquí manejarías el depósito en local
            Toast.makeText(this, "Depósito en Local", Toast.LENGTH_SHORT).show()
        }
    }//onCreate

}//PagoProducto