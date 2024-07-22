package com.example.proyectofinal

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Login : AppCompatActivity() {

    lateinit var EUsuario: EditText
    lateinit var EContraseña: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        EUsuario = findViewById<EditText>(R.id.edtUsuario)
        EContraseña = findViewById<EditText>(R.id.edtpPassword)
    }//OnCreate


    fun principal(vista: View) {
        // Comprobando si alguno de los campos está vacío
        if (EUsuario.text.isEmpty() || EContraseña.text.isEmpty()) {
            Toast.makeText(this, "Usuario o contraseña no válidos.", Toast.LENGTH_SHORT).show()
        } else {
            // Comprobando si las credenciales son de un administrador
            if (EUsuario.text.toString() == "Admin" && EContraseña.text.toString() == "admin") {
                Toast.makeText(this, "Bienvenido Administrador", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Administracion::class.java)
                startActivity(intent)
                finish()

            } else {
                // Si no es administrador, se asume que es un usuario normal
                Toast.makeText(this, "Bienvenido Usuario", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
//principal

    fun Registro(vista: View){
        val intent = Intent(this, Registro::class.java)
        startActivity(intent)
        finish()
        true
    }//Registro
}//Class Login