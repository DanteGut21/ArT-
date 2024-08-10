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