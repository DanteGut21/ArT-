package com.example.proyectofinal

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import database.DatabaseHelper

class Agregar : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var priceEditText: EditText
    private lateinit var stockEditText: EditText
    private lateinit var imageViewProduct: ImageView
    private lateinit var searchEditText: EditText
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar)
        initializeComponents()

        val addButton: Button = findViewById(R.id.addProductButton)
        val searchButton: Button = findViewById(R.id.searchButton)
        databaseHelper = DatabaseHelper(this)

        addButton.setOnClickListener {
            saveOrUpdateProduct()
        }

        searchButton.setOnClickListener {
            searchProduct()
        }
    }

    private fun saveOrUpdateProduct() {
        val name = nameEditText.text.toString()
        val description = descriptionEditText.text.toString()
        val price = priceEditText.text.toString().toDoubleOrNull() ?: 0.0
        val stock = stockEditText.text.toString().toIntOrNull() ?: 0
        val imageName = imageViewProduct.tag?.toString() ?: ""

        if (databaseHelper.productExists(name)) {
            databaseHelper.updateProduct(name, description, price, stock, imageName)
            Toast.makeText(this, "Producto actualizado exitosamente", Toast.LENGTH_SHORT).show()
        } else {
            databaseHelper.addProduct(name, description, price, stock, imageName)
            Toast.makeText(this, "Producto agregado exitosamente", Toast.LENGTH_SHORT).show()
        }
        finish()
    }

    private fun searchProduct() {
        val name = searchEditText.text.toString()
        val product = databaseHelper.getProduct(name)
        if (product != null) {
            nameEditText.setText(product.name)
            descriptionEditText.setText(product.description)
            priceEditText.setText(product.price.toString())
            stockEditText.setText(product.stock.toString())
            val imageResId = resources.getIdentifier(product.image, "drawable", packageName)
            imageViewProduct.setImageResource(imageResId)
            imageViewProduct.tag = product.image
        } else {
            Toast.makeText(this, "Producto no encontrado", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initializeComponents() {
        nameEditText = findViewById(R.id.nameEditText)
        descriptionEditText = findViewById(R.id.descriptionEditText)
        priceEditText = findViewById(R.id.priceEditText)
        stockEditText = findViewById(R.id.stockEditText)
        imageViewProduct = findViewById(R.id.imageViewProduct)
        searchEditText = findViewById(R.id.searchEditText)
    }
}
