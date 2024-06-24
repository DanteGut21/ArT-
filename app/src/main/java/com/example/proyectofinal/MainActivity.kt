package com.example.proyectofinal

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toolbar: Toolbar = findViewById(R.id.tbPrincipal)
        setSupportActionBar(toolbar)
        // Configura la BottomNavigationView
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    // Maneja la acción del botón Home
                    true
                }
                R.id.navigation_profile -> {
                    // Maneja la acción del botón Profile
                    true
                }
                R.id.navigation_cart -> {
                    // Maneja la acción del botón Cart
                    true
                }
                R.id.navigation_more -> {
                    // Maneja la acción del botón More
                    true
                }
                else -> false
            }
        }
    }//onCreate
//
//    fun Login(vista: View){
//        val intent = Intent(this, Login::class.java)
//        startActivity(intent)
//        finish()
//        true
//    }//login

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Infla el menú; esto añade elementos a la barra de acción si está presente.
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_login -> {
                // Acción para abrir LoginActivity
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}//MainActivity