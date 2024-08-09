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

//data class Product(
//    val id: Int,
//    val name: String,
//    val short_description: String?,
//    val long_description: String?,
//    val price: Double,
//    val stock: Int,
//    val imageResId: Int?
//)
