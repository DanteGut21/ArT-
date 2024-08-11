package com.example.proyectofinal

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.proyectofinal.model.Usuario
import com.google.android.material.bottomnavigation.BottomNavigationView
import database.DatabaseHelper

class MainActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper
    private var currentUser: Usuario? = null

    companion object {
        private const val REQUEST_LOGIN = 1001  // Identificador único para la solicitud de login
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        databaseHelper = DatabaseHelper(this)
        setupViews()

        try {
            val email = intent.getStringExtra("userEmail") ?: ""
            val password = intent.getStringExtra("userPassword") ?: ""
            currentUser = databaseHelper.getUserById(email, password)
            updateNavigationVisibility()
        } catch (e: Exception) {
            Toast.makeText(
                this,
                "Error al cargar datos del usuario: ${e.message}",
                Toast.LENGTH_LONG
            ).show()
            e.printStackTrace()
        }

        if (savedInstanceState == null) {
            loadFragment(Principal())  // Carga el fragmento principal solo si no se ha guardado ningún estado
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_LOGIN && resultCode == RESULT_OK) {
            data?.let {
                val email = it.getStringExtra("userEmail") ?: ""
                val password = it.getStringExtra("userPassword") ?: ""
                currentUser = databaseHelper.getUserById(email, password)
                updateNavigationVisibility()
                loadFragment(Principal())  // Carga el fragmento principal basado en el usuario logueado
            }
        }
    }

    private fun setupViews() {
        val toolbar: Toolbar = findViewById(R.id.tbPrincipal)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    loadFragment(Principal())
                    true
                }
                R.id.navigation_store -> {
                    loadFragment(Tienda())
                    true
                }
                R.id.navigation_cart -> {
                    if (currentUser?.tipoUsuario != "admin") {
                        loadFragment(Carrito())
                        true
                    } else false
                }
                R.id.navigation_more -> {
                    loadFragment(Usuario())
                    true
                }
                R.id.navigation_manage -> {
                    if (currentUser?.tipoUsuario == "admin") {
                        loadFragment(GestionP())
                        true
                    } else false
                }
                else -> false
            }
        }
    }

    private fun updateNavigationVisibility() {
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        val manageItem = bottomNavigationView.menu.findItem(R.id.navigation_manage)
        val storeItem = bottomNavigationView.menu.findItem(R.id.navigation_store)
        val cartItem = bottomNavigationView.menu.findItem(R.id.navigation_cart)
        val moreItem = bottomNavigationView.menu.findItem(R.id.navigation_more)

        if (currentUser?.tipoUsuario == "admin") {
            manageItem.isVisible = true
            storeItem.isVisible = false
            cartItem.isVisible = false
            moreItem.isVisible = false
        } else if (currentUser?.tipoUsuario == "usuario") {
            manageItem.isVisible = false
            storeItem.isVisible = true
            cartItem.isVisible = true
            moreItem.isVisible = true
        } else {
            manageItem.isVisible = false
            cartItem.isVisible = false
            moreItem.isVisible = false
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_login -> {
                val intent = Intent(this, Login::class.java)
                startActivityForResult(intent, REQUEST_LOGIN)
                true
            }
            R.id.iconNotifi -> {
                Toast.makeText(this, "Bienvenido a ArTé", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
