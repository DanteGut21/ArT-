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

data class CartItem(
    val id: Int,         // ID del carrito
    val productId: Int,  // ID del producto
    val name: String,
    val price: Double,
    var quantity: Int,
    val imageResId: String?
)

data class Orden(
    val id: Int,
    val idCliente: Int,
    val idCarrito: Int,
    val idDomicilio: Int,
    val fecha: String,  // Puede ser una cadena en formato de fecha, o puedes utilizar LocalDateTime si estás manejando fechas en tu aplicación
    val estado: String  // Los valores pueden ser "Pendiente", "Completa", "Cancelada"
)

data class Direccion(
    val id: Int,
    val calle: String,
    val numeroExterior: String,
    val codigoPostal: String,
    val estado: String,
    val ciudad: String,
    val colonia: String,
    val telefono: String,
    val idCliente: Int
) {
    fun fullAddress(): String {
        return "$calle $numeroExterior, $colonia, $ciudad, $estado, C.P. $codigoPostal, Tel: $telefono"
    }
}
