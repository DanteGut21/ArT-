package com.example.proyectofinal

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {


    companion object {
        const val IS_LOGGED_IN = "isLoggedIn"
    }

    var isLoggedIn: Boolean
        get() = getSharedPreferences("prefs", MODE_PRIVATE).getBoolean(IS_LOGGED_IN, false)
        set(value) {
            getSharedPreferences("prefs", MODE_PRIVATE).edit().putBoolean(IS_LOGGED_IN, value)
                .apply()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }//ViewCompat

        val toolbar: Toolbar = findViewById(R.id.tbPrincipal)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // Configura la BottomNavigationView
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        // Oculta inicialmente el botón de gestión y otros botones hasta verificar el login
        val manageItem = bottomNavigationView.menu.findItem(R.id.navigation_manage)
        val cartItem = bottomNavigationView.menu.findItem(R.id.navigation_cart)
        val moreItem = bottomNavigationView.menu.findItem(R.id.navigation_more)

        manageItem.isVisible = false  // Ocultar hasta que se verifique el login
        cartItem.isVisible = false    // Ocultar hasta que se verifique el login
        moreItem.isVisible = false    // Ocultar hasta que se verifique el login

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
                    loadFragment(Carrito())
                    true
                }
                R.id.navigation_more -> {
                    loadFragment(Usuario())
                    true
                }
                R.id.navigation_manage -> {
                    loadFragment(GestionP())
                    true
                }
                else -> false
            }
        }

        if (savedInstanceState == null) {
            loadFragment(Principal())
        }

        // Comprobar estado de inicio de sesión al iniciar la actividad
        checkLoginStatus()

        isLoggedIn = intent.getBooleanExtra("isLogged", false)
    }//onCreate

    fun checkSession(): Boolean {
        return isLoggedIn
    }

    private fun checkLoginStatus() {
        val isLoggedIn = intent.getBooleanExtra("isLogged", false)
        val isAdmin = intent.getBooleanExtra("isAdmin", false)
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        // El ítem de administración solo es visible para administradores.
        bottomNavigationView.menu.findItem(R.id.navigation_manage).isVisible = isAdmin
        bottomNavigationView.menu.findItem(R.id.navigation_store).isVisible = !isAdmin

        // El ítem del carrito y más opciones solo son visibles para usuarios logueados que no sean administradores.
        bottomNavigationView.menu.findItem(R.id.navigation_cart).isVisible = isLoggedIn && !isAdmin
        bottomNavigationView.menu.findItem(R.id.navigation_more).isVisible = isLoggedIn && !isAdmin
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
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
