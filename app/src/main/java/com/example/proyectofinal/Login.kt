package com.example.proyectofinal

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import database.DatabaseHelper

class Login : AppCompatActivity() {
    lateinit var EUsuario: EditText
    lateinit var EContraseña: EditText
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        databaseHelper = DatabaseHelper(this)
        EUsuario = findViewById(R.id.edtUsuario)
        EContraseña = findViewById(R.id.edtpPassword)
    }

    fun principal(vista: View) {
        val usuario = EUsuario.text.toString()
        val contraseña = EContraseña.text.toString()

        if (usuario.isEmpty() || contraseña.isEmpty()) {
            Toast.makeText(this, "Favor de llenar ambos campos", Toast.LENGTH_SHORT).show()
            return
        }

        val user = databaseHelper.getUser(usuario, contraseña)
        if (user != null) {
            // Guardar estado de sesión en SharedPreferences
            val sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putBoolean("isLoggedIn", true)
            editor.putInt("userId", user.id)
            editor.apply()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Usuario o contraseña incorrectos.", Toast.LENGTH_SHORT).show()
        }
    }//Principal

    fun Registro(vista: View) {
        val intent = Intent(this, Registro::class.java)
        startActivity(intent)
        finish()
        true
    }
}
