package com.example.proyectofinal

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

//    lateinit var BtnPrincipal: Button
//    lateinit var BtnRegistro: Button
//    lateinit var EdtUsuario: EditText
//    lateinit var EdtContraseña: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//
//        BtnPrincipal.findViewById<Button>(R.id.btnInicio)
//        BtnRegistro.findViewById<Button>(R.id.btnRegistro)
//        EdtUsuario.findViewById<EditText>(R.id.edtUsuario)
//        EdtContraseña.findViewById<EditText>(R.id.edtpPassword)
    }

//    fun principal(vista: View){
//        if (EdtUsuario.text.isEmpty() || EdtContraseña.text.isEmpty()){
//            Toast.makeText(this, "Usuario o contraseña no validos.", Toast.LENGTH_SHORT).show()
//
//        }else{
//            Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show()
//            val intent = Intent(this, Inicio::class.java)
//            startActivity(intent)
//            finish()
//            true
//        }
//    }//principal

//    fun Registro(vista: View){
//        val intent = Intent(this, Registro::class.java)
//        startActivity(intent)
//        finish()
//        true
//    }//Registro
}