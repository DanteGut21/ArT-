package com.example.proyectofinal

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import database.DatabaseHelper

class Registro : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var nombreEditText: EditText
    private lateinit var apellidoEditText: EditText
    private lateinit var correoEditText: EditText
    private lateinit var contraseñaEditText: EditText
    private lateinit var confirmarContraseñaEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        databaseHelper = DatabaseHelper(this)

        nombreEditText = findViewById(R.id.edtNombre)
        apellidoEditText = findViewById(R.id.edtApellido)
        correoEditText = findViewById(R.id.edtCorreo)
        contraseñaEditText = findViewById(R.id.edtpPassword2)
        confirmarContraseñaEditText = findViewById(R.id.edtpPassword3)
    }//onCreate

    fun registrarUsuario(vista: View) {
        val nombre = nombreEditText.text.toString().trim()
        val apellido = apellidoEditText.text.toString().trim()
        val correo = correoEditText.text.toString().trim()
        val contraseña = contraseñaEditText.text.toString()
        val confirmarContraseña = confirmarContraseñaEditText.text.toString()

        if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || contraseña.isEmpty()) {
            Toast.makeText(this, "Por favor, rellena todos los campos.", Toast.LENGTH_SHORT).show()
            return
        }

        if (!correo.matches(Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))) {
            Toast.makeText(this, "Introduce un correo electrónico válido.", Toast.LENGTH_SHORT)
                .show()
            return
        }

        if (contraseña.length < 5) {
            Toast.makeText(
                this,
                "La contraseña debe tener al menos 5 caracteres.",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        if (contraseña != confirmarContraseña) {
            Toast.makeText(this, "Las contraseñas no coinciden.", Toast.LENGTH_SHORT).show()
            return
        }

        // Intenta insertar al usuario en la base de datos
        val userId = databaseHelper.insertUser(nombre, apellido, correo, contraseña)
        if (userId != -1L) {
            Toast.makeText(this, "Registro exitoso. Bienvenido, $nombre!", Toast.LENGTH_SHORT)
                .show()
            startActivity(Intent(this, Login::class.java))
            finish()
        } else {
            Toast.makeText(this, "Error en el registro. Inténtalo de nuevo.", Toast.LENGTH_SHORT)
                .show()
        }
    }//registrarUsuario
}//Class Registro