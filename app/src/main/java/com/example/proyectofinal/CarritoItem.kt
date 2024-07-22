package com.example.proyectofinal.model

data class CarritoItem(
    val id: Int,
    val name: String,
    val price: Double,
    val quantity: Int,
    val imageUrl: Int = 0
)

//{
//    fun getImageResource(): Int {
//        return when (name)
//        {
//            "Té de manzanilla" -> R.drawable.te
//            "Té verde" -> R.drawable.te
//            "Té negro" -> R.drawable.te
//            else -> R.drawable.arte
//        }
//    }
//}
//
