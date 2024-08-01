package com.example.proyectofinal

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Login : AppCompatActivity() {
    lateinit var EUsuario: EditText
    lateinit var EContraseña: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        EUsuario = findViewById(R.id.edtUsuario)
        EContraseña = findViewById(R.id.edtpPassword)
    }

    fun principal(vista: View) {
        if (EUsuario.text.isEmpty() || EContraseña.text.isEmpty()) {
            Toast.makeText(this, "Usuario o contraseña no válidos.", Toast.LENGTH_SHORT).show()
            return
        }

        val isAdmin = EUsuario.text.toString() == "Admin" && EContraseña.text.toString() == "admin"
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("isAdmin", isAdmin)
            putExtra("isLogged", true)  // Indicador de que el usuario está logueado
        }

        if (isAdmin) {
            Toast.makeText(this, "Bienvenido Administrador", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Bienvenido Usuario", Toast.LENGTH_SHORT).show()
        }

        startActivity(intent)
        finish()
    }//Principal

    fun Registro(vista: View) {
        val intent = Intent(this, Registro::class.java)
        startActivity(intent)
        finish()
        true
    }//Registro
}//Class login
