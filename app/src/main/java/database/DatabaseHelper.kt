package database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.proyectofinal.model.CartItem
import com.example.proyectofinal.model.Direccion
import com.example.proyectofinal.model.Order
import com.example.proyectofinal.model.Product
import com.example.proyectofinal.model.Usuario

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "arte.db"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Crear tabla de productos sin short_description
        db.execSQL(
            """
            CREATE TABLE productos (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL UNIQUE,
                description TEXT,
                category TEXT,
                price REAL NOT NULL,
                stock INTEGER NOT NULL,
                image TEXT
            );
        """
        )
        //Tabla de usuarios
        db.execSQL(
            """
            CREATE TABLE usuarios (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL UNIQUE,
                apellido TEXT NOT NULL,
                correo TEXT NOT NULL,
                contrasena TEXT NOT NULL,
                tipo_usuario TEXT DEFAULT 'usuario'
            );
        """
        )
        //Tabla carrito
        db.execSQL(
            """
        CREATE TABLE carrito (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            id_producto INTEGER,
            id_cliente INTEGER,
            cantidad INT DEFAULT 1,
            estado_producto TEXT CHECK(estado_producto IN ('Pendiente', 'Pagado', 'Cancelado')) DEFAULT 'Pendiente',
            FOREIGN KEY (id_producto) REFERENCES productos(id),
            FOREIGN KEY (id_cliente) REFERENCES usuarios(id)
        );
    """
        )
        //Tabla ordenes
        db.execSQL(
            """
        CREATE TABLE ordenes (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            id_cliente INTEGER,
            id_carrito INTEGER,
            fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
            estado TEXT CHECK(estado IN ('Pendiente', 'Completa', 'Cancelada')) DEFAULT 'Pendiente',
            FOREIGN KEY (id_cliente) REFERENCES usuarios(id),
            FOREIGN KEY (id_carrito) REFERENCES carrito(id)
        );"""
        )

        db.execSQL(
            """            
        CREATE TABLE direcciones (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            calle TEXT NOT NULL,
            numero_exterior TEXT NOT NULL,
            codigo_postal TEXT NOT NULL,
            estado TEXT NOT NULL,
            ciudad TEXT NOT NULL,
            colonia TEXT NOT NULL,
            telefono TEXT NOT NULL,
            id_cliente INTEGER NOT NULL,
            FOREIGN KEY (id_cliente) REFERENCES usuarios(id)
        );"""
        )

        Log.d("DatabaseOperation", "Database created successfully")
        initializeDatabase(db)
    }//OnCreate

    private fun initializeDatabase(db: SQLiteDatabase) {
        val products = listOf(
            Product(
                1,
                "Dragon Ball",
                "Como equilibrista en la cuerda floja, aparece un crisantemo rosa " +
                        "suspendido en un arco de jazmines. Una flor de caléndula espera ansiosa " +
                        "recibirlo por si no puede evitar caer. Cada perla está formada por hojas de" +
                        " Té atadas a mano con algodón, con flores naturales ocultas en su interior.",
                "Floreciente",
                100.0,
                10,
                "dragonball"
            ),
            Product(
                2,
                "Té Floreciente Jazmín",
                "Hecho a mano enrollando las hojas con flores de jazmín en orbes de " +
                        "loto, un sabor dulce y refrescante de cuerpo completo, y un agradable " +
                        "retrogusto floral de larga duración, lleno de antioxidantes para aliviar el" +
                        " estrés y vigorizar la juventud.",
                "Floreciente",
                409.25,
                5,
                "jazmin"
            ),
            Product(
                3,
                "Té Floreciente Blanco",
                "Cada bolita puede ser infusionada hasta 3 veces, proporcionando un " +
                        "total de al menos 1 litro de té, cada porción es un manojo de finos brotes " +
                        "de té blanco cosechados en primavera, que revelan una o varias flores " +
                        "ocultas al ser infusionadas.",
                "Floreciente",
                382.16,
                10,
                "blanco"
            ),
            Product(
                4,
                "Té Floreciente Pineapple",
                "Un té verde con flores magistralmente atadas a mano para deleitar " +
                        "todos sus sentidos. Una vez que la bola de té se coloca en agua recién " +
                        "hervida, el té “florece” directamente en su tetera o vaso como una flor. " +
                        "La hermosa flor se despliega y libera un fino té verde adornado con dos " +
                        "torres de flores, comenzando con un centro amarillo, un clavel rosa y un " +
                        "jazmín. Su sabor está lleno de dulce sabor a piña y delicado té verde jazmín.",
                "Floreciente",
                126.48,
                10, // Asumiendo un stock ejemplo
                "pineapple"
            ),
            Product(
                5,
                "Té Floreciente Mango",
                "Nuestro té floreciente de mango crea un verdadero ramo de flores. " +
                        "Cada té está atado a mano con una flor de amaranto y lleno del aroma " +
                        "afrutado del mango. Este té lleva el espíritu relajante del jardín.",
                "Floreciente",
                681.06,
                10, // Asumiendo un stock ejemplo
                "mango"
            ),
            Product(
                6,
                "Fitelym Pro",
                "Preparada con el fin de dirigir al cuerpo hacia una desintoxicación" +
                        " y limpieza, a través de la depuración del colon y la eliminación de " +
                        "toxinas del organismo, retirando residuos almacenados en nuestro " +
                        "intestino, además de acelerar la pérdida de grasa.",
                "Soluble",
                529.88,
                10, // Asumiendo un stock ejemplo
                "fitelymsoluble"
            ),
            Product(
                7,
                "Negro Frutos Rojos",
                "Se prepara en cómodos sticks monodosis que siempre podrás llevar " +
                        "contigo y añadir a una botella de agua que pidas en una terraza para " +
                        "refrescarte, o en un vaso que tengas en la oficina. No hay una forma más" +
                        " cómoda y sabrosa de darle sabor a la vida sin tener que recurrir a las " +
                        "bebidas con gas o con azúcar. Ni azúcar, ni calorías, el complemento " +
                        "perfecto para ti. Además, en verano, ¡está delicioso con hielo!",
                "Soluble",
                170.11,
                10, // Asumiendo un stock ejemplo
                "frojos"
            ),
            Product(
                8,
                "Rojo Naranja",
                "El Té Soluble Rojo Naranja es ideal para todos lo que andamos " +
                        "buscando disfrutar de una bebida sana y refrescante sin azúcares o gas." +
                        " Cada caja contiene 12 cómodos sticks individuales que podrás llevar" +
                        " a cualquier parte, en el bolso, en la cartera para disfrutar en cualquier" +
                        " lugar. Solo necesitas agua, hielo en verano y en pocos segundos podrás " +
                        "disfrutar del delicioso sabor de té rojo con naranja.",
                "Soluble",
                170.11,
                10, // Asumiendo un stock ejemplo
                "rojonaranjasoluble"
            ),
            Product(
                9,
                "Verde Manzana",
                "El Té Soluble Verde Manzana es una maravilla. Sustituye las bebidas" +
                        " azucaradas y con gas por estos cómodos sticks monodosis que podrás" +
                        " disolver en agua allá donde estés.",
                "Soluble",
                170.11,
                10, // Asumiendo un stock ejemplo
                "verdemanzanasoluble"
            ),
            Product(
                10,
                "Matcha Limón",
                "El té soluble matcha limón es perfecto para los que buscan una bebida" +
                        " saludable, rica y sin calorías. Una alternativa perfecta a los refrescos" +
                        " azucarados y con gas.",
                "Soluble",
                170.11,
                10, // Asumiendo un stock ejemplo
                "matcha"
            ),
            Product(
                11,
                "Jazmín",
                "Con un exótico sabor y aroma a jazmín, ideal para degustar acompañando" +
                        " un curry o comida cantonesa. Tiene un alto contenido de antioxidantes y" +
                        " poco estimulante; con propiedades antibacteriales y desintoxicantes que " +
                        "ayudan a relajar el sistema nervioso.",
                "Bolsa",
                32.00,
                10, // Asumiendo un stock ejemplo
                "jazminbolsa"
            ),
            Product(
                12,
                "Menta Fresh",
                "Perfecto para preparar la típica bebida marroquí que se originó" +
                        " cuando el mandarín chino regaló al gobernante de Marruecos Té verde tipo " +
                        "'Gunpowder'. Mezcla de hojas de menta, té verde y sabor a menta.",
                "Bolsa",
                32.00,
                10, // Asumiendo un stock ejemplo
                "mentabolsa"
            ),
            Product(
                13,
                "Té Negro Arabic",
                "Esta cosechado a mano y combinado con aceite de bergamota de" +
                        " Calabria, Italia - Cuenta con ingredientes naturales - Certificación Kosher",
                "Bolsa",
                52.00,
                10, // Asumiendo un stock ejemplo
                "negrobolsa"
            ),
            Product(
                14,
                "Hierbabuena",
                "El té de hierbabuena sin cafeína en caja. Es muy fácil de preparar," +
                        " solo añade un sobrecito en una taza con agua caliente y deja reposar unos" +
                        " minutos, si deseas un toque dulce agrega un poco de azúcar, miel o" +
                        " incluso edulcorante para mesa.",
                "Bolsa",
                44.00,
                10, // Asumiendo un stock ejemplo
                "hierbabuenabolsita"
            ),
            Product(
                15,
                "Piña",
                "El té de piña de Tadin resalta con notas de piña, menta y hierba de" +
                        " limón para un sabor tropical equilibrado. Servido caliente o frío, este" +
                        " té es perfecto para usar durante todo el año.",
                "Bolsa",
                33.00,
                10, // Asumiendo un stock ejemplo
                "pineapplebolsa"
        )
        )
        products.forEach { product ->
            ContentValues().apply {
                put("name", product.name)
                put("description", product.description)
                put("category", product.category)
                put("price", product.price)
                put("stock", product.stock)
                put("image", product.imageResId)
            }.also { values ->
                db.insert("productos", null, values)
            }
        }

        val users = listOf(
            ContentValues().apply {
                put("nombre", "Admin")
                put("apellido", "User")
                put("correo", "admin@arte.com")
                put("contrasena", "admin")
                put("tipo_usuario", "admin")
            },
            ContentValues().apply {
                put("nombre", "User")
                put("apellido", "User")
                put("correo", "user@example.com")
                put("contrasena", "user123")
            },
            ContentValues().apply {
                put("nombre", "Dante")
                put("apellido", "Gutierrez")
                put("correo", "dangt@gmail.com")
                put("contrasena", "12345")
            }
        )
        users.forEach { values ->
            db.insert("usuarios", null, values)
        }

        val orders = listOf(
            ContentValues().apply {
                put("id_cliente", 1)  // Asumiendo que el ID de usuario 1 existe
                put("id_carrito", 1)  // Asumiendo que el ID de carrito 1 existe
                put("estado", "Pendiente")
            },
            ContentValues().apply {
                put("id_cliente", 1)
                put("id_carrito", 2)
                put("estado", "Completa")
            },
            ContentValues().apply {
                put("id_cliente", 2)  // Asumiendo que el ID de usuario 2 existe
                put("id_carrito", 3)
                put("estado", "Cancelada")
            }
        )

        // Insertar los datos iniciales en la tabla de órdenes
        orders.forEach { orderValues ->
            db.insert("ordenes", null, orderValues)
        }

    }//initializeDatabase

    fun getAllProducts(): List<Product> {
        val productList = mutableListOf<Product>()
        val db = readableDatabase
        try {
            val cursor = db.query("productos", null, null, null, null, null, null)
            if (cursor.moveToFirst()) {
                do {
                    val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                    val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                    val description = cursor.getString(cursor.getColumnIndexOrThrow("description"))
                    val category = cursor.getString(cursor.getColumnIndexOrThrow("category"))
                    val price = cursor.getDouble(cursor.getColumnIndexOrThrow("price"))
                    val stock = cursor.getInt(cursor.getColumnIndexOrThrow("stock"))
                    val imageResId = cursor.getString(cursor.getColumnIndexOrThrow("image"))

                    productList.add(
                        Product(
                            id,
                            name,
                            description,
                            category,
                            price,
                            stock,
                            imageResId
                        )
                    )
                } while (cursor.moveToNext())
            }
            cursor.close()
        } catch (e: Exception) {
            Log.e("DatabaseError", "Error reading from database", e)
        } finally {
            db.close()
        }
        return productList
    }//getAllProducts
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS productos")
        db.execSQL("DROP TABLE IF EXISTS usuarios")
        onCreate(db)
    }//onUpgrade
    fun getProduct(productId: Int): Product? {
        val db = this.readableDatabase
        val cursor = db.query(
            "productos",  // Nombre de la tabla
            null,         // Todas las columnas
            "id = ?",     // Cláusula WHERE
            arrayOf(productId.toString()),  // Valores para la cláusula WHERE
            null,         // groupBy
            null,         // having
            null          // orderBy
        )
        var product: Product? = null
        if (cursor.moveToFirst()) {
            product = Product(
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                name = cursor.getString(cursor.getColumnIndexOrThrow("name")),
                description = cursor.getString(cursor.getColumnIndexOrThrow("description")),
                category = cursor.getString(cursor.getColumnIndexOrThrow("category")),
                price = cursor.getDouble(cursor.getColumnIndexOrThrow("price")),
                stock = cursor.getInt(cursor.getColumnIndexOrThrow("stock")),
                imageResId = cursor.getString(cursor.getColumnIndexOrThrow("image"))
            )
        }
        cursor.close()
        db.close()
        return product
    }//Producto
    fun insertUser(nombre: String, apellido: String, correo: String, contrasena: String): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("nombre", nombre)
            put("apellido", apellido)
            put("correo", correo)
            put("contrasena", contrasena)
        }
        val userId = db.insert("usuarios", null, values)
        db.close()
        return userId
    }//Login
    fun getUser(correo: String, contrasena: String): Usuario? {
        val db = readableDatabase
        val cursor = db.query(
            "usuarios",
            null,
            "correo = ? AND contrasena = ?",
            arrayOf(correo, contrasena),
            null,
            null,
            null
        )

        var user: Usuario? = null
        if (cursor.moveToFirst()) {
            user = Usuario(
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                apellido = cursor.getString(cursor.getColumnIndexOrThrow("apellido")),
                correo = cursor.getString(cursor.getColumnIndexOrThrow("correo")),
                contrasena = cursor.getString(cursor.getColumnIndexOrThrow("contrasena")),
                tipoUsuario = cursor.getString(cursor.getColumnIndexOrThrow("tipo_usuario"))
            )
        }
        cursor.close()
        db.close()
        return user
    }//Login
    fun addDireccion(direccion: Direccion): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("calle", direccion.calle)
            put("numero_exterior", direccion.numeroExterior)
            put("codigo_postal", direccion.codigoPostal)
            put("estado", direccion.estado)
            put("ciudad", direccion.ciudad)
            put("colonia", direccion.colonia)
            put("telefono", direccion.telefono)
            put("id_cliente", direccion.idCliente)
        }
        try {
            return db.insertOrThrow("direcciones", null, values)
        } catch (e: Exception) {
            Log.e("DBError", "Error inserting address: ${e.message}")
            return -1L
        } finally {
            db.close()
        }
    }//Direccion

    fun getAddresses(userId: Int): List<Direccion> {
        val addresses = mutableListOf<Direccion>()
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(
                "SELECT * FROM direcciones WHERE id_cliente = ?",
                arrayOf(userId.toString())
            )
            if (cursor.moveToFirst()) {
                do {
                    val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                    val calle = cursor.getString(cursor.getColumnIndexOrThrow("calle"))
                    val numeroExterior =
                        cursor.getString(cursor.getColumnIndexOrThrow("numero_exterior"))
                    val codigoPostal =
                        cursor.getString(cursor.getColumnIndexOrThrow("codigo_postal"))
                    val estado = cursor.getString(cursor.getColumnIndexOrThrow("estado"))
                    val ciudad = cursor.getString(cursor.getColumnIndexOrThrow("ciudad"))
                    val colonia = cursor.getString(cursor.getColumnIndexOrThrow("colonia"))
                    val telefono = cursor.getString(cursor.getColumnIndexOrThrow("telefono"))
                    val idCliente = cursor.getInt(cursor.getColumnIndexOrThrow("id_cliente"))

                    addresses.add(
                        Direccion(
                            id,
                            calle,
                            numeroExterior,
                            codigoPostal,
                            estado,
                            ciudad,
                            colonia,
                            telefono,
                            idCliente
                        )
                    )
                } while (cursor.moveToNext())
            }
        } catch (e: Exception) {
            Log.e("DatabaseError", "Error fetching addresses: ${e.message}")
        } finally {
            cursor?.close()
            db.close()
        }
        return addresses
    }//Direccion
    fun getUserById(userId: Int): Usuario? {
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.query(
                "usuarios",
                null,
                "id = ?",
                arrayOf(userId.toString()),
                null,
                null,
                null
            )
            if (cursor.moveToFirst()) {
                return Usuario(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                    apellido = cursor.getString(cursor.getColumnIndexOrThrow("apellido")),
                    correo = cursor.getString(cursor.getColumnIndexOrThrow("correo")),
                    contrasena = cursor.getString(cursor.getColumnIndexOrThrow("contrasena")),
                    tipoUsuario = cursor.getString(cursor.getColumnIndexOrThrow("tipo_usuario"))
                )
            }
        } finally {
            cursor?.close()
            db.close()
        }
        return null
    }//MainActivity
    fun addToCart(productId: Int, userId: Int, cantidad: Int) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put("id_producto", productId)
            put("id_cliente", userId)
            put("cantidad", cantidad)
            put("estado_producto", "Pendiente")
        }
        db.insert("carrito", null, values)
        db.close()
    }//Producto
    fun getProductStock(productId: Int): Int {
        val db = this.readableDatabase
        val cursor = db.query(
            "productos",
            arrayOf("stock"),
            "id = ?",
            arrayOf(productId.toString()),
            null,
            null,
            null
        )
        var stock = 0
        if (cursor.moveToFirst()) {
            stock = cursor.getInt(cursor.getColumnIndexOrThrow("stock"))
        }
        cursor.close()
        db.close()
        return stock
    }//Producto
    fun getCartProducts(userId: Int): MutableList<CartItem> {
        val db = this.readableDatabase
        val cartItems = mutableListOf<CartItem>()
        val query = """
        SELECT carrito.id as cartId, productos.id as productId, name, price, cantidad, image
        FROM carrito
        JOIN productos ON carrito.id_producto = productos.id
        LEFT JOIN ordenes ON carrito.id = ordenes.id_carrito
        WHERE carrito.id_cliente = ? AND (ordenes.estado IS NULL OR ordenes.estado != 'Completa')
    """
        val cursor = db.rawQuery(query, arrayOf(userId.toString()))

        if (cursor.moveToFirst()) {
            do {
                val product = CartItem(
                    id = cursor.getInt(cursor.getColumnIndex("cartId")),
                    productId = cursor.getInt(cursor.getColumnIndex("productId")),
                    name = cursor.getString(cursor.getColumnIndex("name")),
                    price = cursor.getDouble(cursor.getColumnIndex("price")),
                    quantity = cursor.getInt(cursor.getColumnIndex("cantidad")),
                    imageResId = cursor.getString(cursor.getColumnIndex("image"))
                )
                cartItems.add(product)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return cartItems
    }//Carrito
    fun updateCartItemQuantity(cartId: Int, newQuantity: Int) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("cantidad", newQuantity)
        db.update("carrito", values, "id = ?", arrayOf(cartId.toString()))
        db.close()
    }
    fun removeFromCart(cartId: Int) {
        val db = this.writableDatabase
        db.delete("carrito", "id = ?", arrayOf(cartId.toString()))
        db.close()
    }
    fun updateProductStock(productId: Int, stockChange: Int) {
        val db = this.writableDatabase
        val newStock = getProductStock(productId) - stockChange
        val values = ContentValues()
        values.put("stock", newStock)
        db.update("productos", values, "id = ?", arrayOf(productId.toString()))
        db.close()
    }//Producto

    fun cancelCartAndCreateOrder(userId: Int) {
        val db = this.writableDatabase
        db.beginTransaction()
        try {
            val cartCursor = db.query(
                "carrito",
                arrayOf("id"),
                "id_cliente=? AND estado_producto = 'Pendiente'",
                arrayOf(userId.toString()),
                null,
                null,
                null
            )
            val cartIds = mutableListOf<Int>()
            while (cartCursor.moveToNext()) {
                cartIds.add(cartCursor.getInt(cartCursor.getColumnIndex("id")))
            }
            cartCursor.close()

            cartIds.forEach { cartId ->
                // Crear una orden cancelada si no existe
                val orderCursor = db.query(
                    "ordenes",
                    arrayOf("id"),
                    "id_carrito=?",
                    arrayOf(cartId.toString()),
                    null,
                    null,
                    null
                )
                if (!orderCursor.moveToFirst()) {
                    val orderValues = ContentValues().apply {
                        put("id_cliente", userId)
                        put("id_carrito", cartId)
                        put("estado", "Cancelada")
                    }
                    db.insert("ordenes", null, orderValues)
                }
                orderCursor.close()

                // Actualizar el carrito a cancelado
                val cartValues = ContentValues()
                cartValues.put("estado_producto", "Cancelado")
                db.update("carrito", cartValues, "id=?", arrayOf(cartId.toString()))
            }
            db.setTransactionSuccessful()
        } catch (e: Exception) {
            Log.e("DatabaseError", "Error updating cart and orders: ${e.message}")
        } finally {
            db.endTransaction()
            db.close()
        }
    }//carrito
    fun updateCartAndOrdersStatus(userId: Int, status: String) {
        val db = this.writableDatabase
        db.beginTransaction()
        try {
            // Obtener todos los carritos activos para este usuario
            val cartCursor = db.query(
                "carrito",
                arrayOf("id"),
                "id_cliente=?",
                arrayOf(userId.toString()),
                null,
                null,
                null
            )
            val cartIds = mutableListOf<Int>()
            while (cartCursor.moveToNext()) {
                cartIds.add(cartCursor.getInt(cartCursor.getColumnIndex("id")))
            }
            cartCursor.close()

            // Verificar si ya existe una orden para cada carrito y crearla si no existe
            cartIds.forEach { cartId ->
                val orderExistsCursor = db.query(
                    "ordenes",
                    arrayOf("id"),
                    "id_carrito=?",
                    arrayOf(cartId.toString()),
                    null,
                    null,
                    null
                )
                if (!orderExistsCursor.moveToFirst()) {
                    val values = ContentValues().apply {
                        put("id_cliente", userId)
                        put("id_carrito", cartId)
                        put("estado", "Pendiente")  // Estado inicial cuando se crea la orden
                    }
                    db.insert("ordenes", null, values)
                }
                orderExistsCursor.close()
            }

            // Actualizar el estado de las órdenes y el carrito
            val valuesOrders = ContentValues()
            valuesOrders.put("estado", status)
            db.update("ordenes", valuesOrders, "id_cliente=?", arrayOf(userId.toString()))

            val valuesCart = ContentValues()
            valuesCart.put("estado_producto", status)
            db.update("carrito", valuesCart, "id_cliente=?", arrayOf(userId.toString()))

            db.setTransactionSuccessful()
        } catch (e: Exception) {
            Log.e("DatabaseError", "Error updating cart and orders: ${e.message}")
        } finally {
            db.endTransaction()
            db.close()
        }
    }//Pago/Carrito

    fun createOrUpdateOrder(userId: Int, paymentMethod: String) {
        val db = writableDatabase
        db.beginTransaction()
        try {
            // Verificar si existe una orden pendiente para el usuario
            val cursor = db.query(
                "ordenes",
                arrayOf("id"),
                "id_cliente=? AND estado='Pendiente'",
                arrayOf(userId.toString()),
                null,
                null,
                null
            )

            val orderValues = ContentValues()
            if (cursor.moveToFirst()) {
                // Si existe, actualizar el método de pago
                val orderId = cursor.getInt(cursor.getColumnIndex("id"))
                orderValues.put(
                    "payment_method",
                    paymentMethod
                ) // Asumiendo que existe una columna para método de pago
                db.update("ordenes", orderValues, "id=?", arrayOf(orderId.toString()))
            } else {
                // Si no existe, crear una nueva orden
                orderValues.apply {
                    put("id_cliente", userId)
                    put("estado", "Pendiente")
                    put(
                        "payment_method",
                        paymentMethod
                    ) // Asegúrate de tener esta columna en tu tabla
                }
                db.insert("ordenes", null, orderValues)
            }
            cursor.close()
            db.setTransactionSuccessful()
        } catch (e: Exception) {
            Log.e("DatabaseError", "Error updating or creating order: ${e.message}")
        } finally {
            db.endTransaction()
            db.close()
        }
    }

    fun ensureCartExists(userId: Int, productId: Int): Int {
        val db = writableDatabase
        // Primero verifica si existe un carrito activo para este usuario y producto
        val cursor = db.rawQuery(
            "SELECT id FROM carrito WHERE id_cliente = ? AND id_producto = ? AND estado_producto = 'Pendiente'",
            arrayOf(userId.toString(), productId.toString())
        )
        if (cursor.moveToFirst()) {
            val cartId = cursor.getInt(cursor.getColumnIndex("id"))
            cursor.close()
            return cartId
        } else {
            cursor.close()
            // No existe, crea un nuevo carrito
            val values = ContentValues()
            values.put("id_cliente", userId)
            values.put("id_producto", productId)
            values.put("cantidad", 1) // Suponiendo una cantidad predeterminada de 1
            values.put("estado_producto", "Pendiente")
            val newCartId = db.insert("carrito", null, values).toInt()
            return newCartId
        }
    }

    fun getAllOrders(): List<Order> {
        val orders = mutableListOf<Order>()
        val db = this.readableDatabase
        val query = "SELECT * FROM ordenes"
        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex("id"))
                val idCliente = cursor.getInt(cursor.getColumnIndex("id_cliente"))
                val idCarrito = cursor.getInt(cursor.getColumnIndex("id_carrito"))
                val fecha = cursor.getString(cursor.getColumnIndex("fecha"))
                val estado = cursor.getString(cursor.getColumnIndex("estado"))
                orders.add(Order(id, idCliente, idCarrito, fecha, estado))
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return orders
    }//Ordenes


}//DatabaseHelper