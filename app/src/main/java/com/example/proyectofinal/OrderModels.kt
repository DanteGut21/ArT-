package com.example.proyectofinal.model

data class Item(
    val name: String,
    val quantity: Int
)

data class Order(
    val userName: String,
    val items: List<Item>,
    val status: String
)
data class Product(
    val id: Int,
    val name: String,
    val description: String?,
    val category: String?,
    val price: Double,
    val stock: Int,
    val imageResId: String?
)
data class Usuario(
    val id: Int,
    val nombre: String,
    val apellido: String,
    val correo: String,
    val contrasena: String,
    val tipoUsuario: String
)