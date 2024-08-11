package database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
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
            fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
            estado TEXT CHECK(estado IN ('Pendiente', 'Completa', 'Cancelada')) DEFAULT 'Pendiente',
            FOREIGN KEY (id_cliente) REFERENCES usuarios(id)
        );
    """
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
            }
        )
        users.forEach { values ->
            db.insert("usuarios", null, values)
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
    }//getProduct

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
    }//insertUser

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
    }//getUser

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
    }//getUserById

    fun addToCart(productId: Int, userId: Int, cantidad: Int) {
        val db = this.writableDatabase
        db.beginTransaction() // Usar transacciones para garantizar la integridad de la data
        try {
            // Actualizar el stock del producto
            val newStock = getProductStock(productId) - cantidad
            if (newStock >= 0) { // Solo proceder si hay suficiente stock
                val stockValues = ContentValues()
                stockValues.put("stock", newStock)
                db.update("productos", stockValues, "id = ?", arrayOf(productId.toString()))

                // Añadir al carrito
                val values = ContentValues().apply {
                    put("id_producto", productId)
                    put("id_cliente", userId)
                    put("cantidad", cantidad)
                }
                db.insertOrThrow("carrito", null, values)
                db.setTransactionSuccessful() // Marcar la transacción como exitosa
            } else {
                throw Exception("No hay suficiente stock disponible")
            }
        } catch (e: Exception) {
            Log.e("DatabaseError", "Error al añadir al carrito: ${e.message}")
        } finally {
            db.endTransaction()
            db.close()
        }
    }//addToCart

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
    }

    fun updateProductStock(productId: Int, quantityChange: Int) {
        val db = writableDatabase
        try {
            db.execSQL(
                "UPDATE productos SET stock = stock + ? WHERE id = ?",
                arrayOf(quantityChange, productId)
            )
        } catch (e: Exception) {
            Log.e("DatabaseError", "Error updating stock: ${e.localizedMessage}")
        } finally {
            db.close()
        }
    }

}//DatabaseHelper