package com.example.proyectofinal

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
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

//        val searchView: SearchView = findViewById(R.id.svBusqueda)
//        // Cambiar el color del texto
//        val searchText = searchView.findViewById(androidx.appcompat.R.id.search_src_text) as android.widget.TextView
//        searchText.setTextColor(Color.GREEN)  // Cambiar a tu color preferido
//        searchText.setHintTextColor(Color.GRAY)  // Cambiar a tu color preferido

        val toolbar: Toolbar = findViewById(R.id.tbPrincipal)
        setSupportActionBar(toolbar)
        // Oculta el título predeterminado del Toolbar
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // Configura la BottomNavigationView
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    loadFragment(Principal())
                    true
                }
                R.id.navigation_profile -> {
                    loadFragment(Tienda())
                    true
                }
                R.id.navigation_cart -> {
                    loadFragment(Carrito())
                    true
                }
                R.id.navigation_more -> {
                    loadFragment(Usuario())
                    true
                }
                else -> false
            }
        }

        // Cargar el fragmento inicial
        if (savedInstanceState == null) {
            loadFragment(Principal())
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

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
}
