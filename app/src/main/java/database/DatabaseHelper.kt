package database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.proyectofinal.model.Product

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "arte.db"
        private const val DATABASE_VERSION = 1
    }//companion object

    override fun onCreate(db: SQLiteDatabase) {
        // Creación de tablas
        db.execSQL(
            "CREATE TABLE users (" +
                    "id INTEGER PRIMARY KEY," +
                    "username TEXT NOT NULL UNIQUE," +
                    "email TEXT NOT NULL UNIQUE," +
                    "password TEXT NOT NULL," +
                    "role TEXT NOT NULL CHECK(role IN ('admin', 'usernormal')));"
        )

        db.execSQL(
            "CREATE TABLE productos (" +
                    "id INTEGER PRIMARY KEY," +
                    "name TEXT NOT NULL UNIQUE," + // Asegurarse que el nombre del producto sea único
                    "description TEXT," +
                    "price REAL NOT NULL," +
                    "stock INTEGER NOT NULL," +
                    "image TEXT);"
        )

        db.execSQL(
            "CREATE TABLE carrito (" +
                    "id INTEGER PRIMARY KEY," +
                    "user_id INTEGER NOT NULL," +
                    "product_id INTEGER NOT NULL," +
                    "cantidad INTEGER NOT NULL," +
                    "status TEXT NOT NULL CHECK(status IN ('falta de pago', 'pagado', 'cancelado'))," +
                    "direccion_envio TEXT," +
                    "FOREIGN KEY (user_id) REFERENCES users(id)," +
                    "FOREIGN KEY (product_id) REFERENCES productos(id));"
        )

        // Inserciones predefinidas con INSERT OR IGNORE para evitar duplicados
        db.execSQL(
            "INSERT OR IGNORE INTO users (username, email, password, role) VALUES " +
                    "('admin', 'admin@example.com', 'adminpassword', 'admin')," +
                    "('usernormal', 'usernormal@example.com', 'userpassword', 'usernormal');"
        )

        db.execSQL(
            "INSERT OR IGNORE INTO productos (name, description, price, stock) VALUES " +
                    "('Producto 1', 'Te blomming Dragon Ball', 100.0, 10)," +
                    "('Producto 2', 'Infusion frutos rojos', 55.0, 5)," +
                    "('Producto 3', 'Matcha', 200.0, 5)," +
                    "('Producto 4', 'TE NEGRO ARABIC 1001 NOCHES', 400.0, 5)," +
                    "('Producto 5', 'INFUSION MANGO Y VAINILLA', 350.0, 5);"
        )
    }//onCreate

    fun addProduct(name: String, description: String, price: Double, stock: Int, image: String) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put("name", name)
            put("description", description)
            put("price", price)
            put("stock", stock)
            put("image", image)
        }
        db.insert("productos", null, values)
        db.close()
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS users")
        db.execSQL("DROP TABLE IF EXISTS productos")
        db.execSQL("DROP TABLE IF EXISTS carrito")
        onCreate(db)
    }//onUpgrade

    fun productExists(name: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT 1 FROM productos WHERE name = ?", arrayOf(name))
        val exists = cursor.moveToFirst()
        cursor.close()
        db.close()
        return exists
    }//productExists

    fun getProduct(name: String): Product? {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM productos WHERE name = ?", arrayOf(name))
        val product: Product? = if (cursor.moveToFirst()) {
            val idIndex = cursor.getColumnIndex("id")
            val nameIndex = cursor.getColumnIndex("name")
            val descIndex = cursor.getColumnIndex("description")
            val priceIndex = cursor.getColumnIndex("price")
            val stockIndex = cursor.getColumnIndex("stock")
            val imageIndex = cursor.getColumnIndex("image")

            // Asegurarse que los índices son válidos
            if (idIndex != -1 && nameIndex != -1 && priceIndex != -1 && stockIndex != -1) {
                Product(
                    id = cursor.getInt(idIndex),
                    name = cursor.getString(nameIndex),
                    description = if (descIndex != -1) cursor.getString(descIndex) else null,
                    price = cursor.getDouble(priceIndex),
                    stock = cursor.getInt(stockIndex),
                    image = if (imageIndex != -1) cursor.getString(imageIndex) else null
                )
            } else {
                null
            }
        } else {
            null
        }
        cursor.close()
        db.close()
        return product
    }


    fun updateProduct(name: String, description: String, price: Double, stock: Int, image: String) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put("description", description)
            put("price", price)
            put("stock", stock)
            put("image", image)
        }
        db.update("productos", values, "name = ?", arrayOf(name))
        db.close()
    }//updateProduct


}//Class DatabaseHelper
