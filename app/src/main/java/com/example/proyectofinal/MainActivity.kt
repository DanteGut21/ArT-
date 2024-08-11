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
    private var backPressedTime: Long = 0
    private var backToast: Toast? = null

    companion object {
        private const val REQUEST_LOGIN = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        databaseHelper = DatabaseHelper(this)
        setupViews()
        checkLoginStatus()
    }

    private fun checkLoginStatus() {
        val sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        val userId = sharedPreferences.getInt("userId", -1)
        if (userId != -1) {
            currentUser = databaseHelper.getUserById(userId)
            updateNavigationVisibility()
            loadFragment(Principal())  // Asegura que se carga el fragmento principal si el usuario está logueado
        } else {
            // Si no hay usuario logueado o se ha cerrado la sesión, carga un fragmento por defecto
            currentUser = null
            updateNavigationVisibility()
            loadFragment(Principal())  // Carga el fragmento principal como default
        }
    }


    private fun setupViews() {
        val toolbar: Toolbar = findViewById(R.id.tbPrincipal)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        setupBottomNavigationView()
    }

    private fun setupBottomNavigationView() {
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

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast?.cancel()
            super.onBackPressed()
        } else {
            backToast = Toast.makeText(
                baseContext,
                "Presione Atrás nuevamente para salir.",
                Toast.LENGTH_SHORT
            )
            backToast?.show()
        }
        backPressedTime = System.currentTimeMillis()
    }

    private fun updateNavigationVisibility() {
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.menu.apply {
            findItem(R.id.navigation_manage).isVisible = currentUser?.tipoUsuario == "admin"
            findItem(R.id.navigation_store).isVisible = currentUser?.tipoUsuario == "usuario"
            findItem(R.id.navigation_cart).isVisible = currentUser?.tipoUsuario == "usuario"
            findItem(R.id.navigation_more).isVisible = currentUser?.tipoUsuario == "usuario"
        }
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