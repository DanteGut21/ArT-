package com.example.proyectofinal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

data class Product(
    val id: Int,
    val name: String,
    val shortDescription: String?,
    val longDescription: String?,
    val category: String?,
    val price: Double,
    val stock: Int,
    val imageResId: Int
)

class Tienda : Fragment() {

    private fun getProducts(): List<Product> {
        return listOf(
            Product(
                1,
                "Dragon Ball",
                "Té Floreciente",
                "Como equilibrista en la cuerda floja, aparece un crisantemo rosa " +
                        "suspendido en un arco de jazmines. Una flor de caléndula espera ansiosa " +
                        "recibirlo por si no puede evitar caer. Cada perla está formada por hojas de" +
                        " Té atadas a mano con algodón, con flores naturales ocultas en su interior.",
                "Floreciente",
                100.0,
                10,
                R.drawable.dragonball
            ),
            Product(
                2,
                "Té Floreciente Jazmín",
                "La flor del jazmín tiene propiedades antioxidantes, " +
                        "antienvejecimiento y para reducir el estrés.",
                "Hecho a mano enrollando las hojas con flores de jazmín en orbes de " +
                        "loto, un sabor dulce y refrescante de cuerpo completo, y un agradable " +
                        "retrogusto floral de larga duración, lleno de antioxidantes para aliviar el" +
                        " estrés y vigorizar la juventud.",
                "Floreciente",
                409.25,
                5,
                R.drawable.jazmin
            ),
            Product(
                3,
                "Té Floreciente Blanco",
                "Descubre la exquisitez de nuestros tés florecientes elaborados con té" +
                        " blanco Bai Hao Yin Zhen proveniente de la provincia de Fujian en China.",
                "Cada bolita puede ser infusionada hasta 3 veces, proporcionando un " +
                        "total de al menos 1 litro de té, cada porción es un manojo de finos brotes " +
                        "de té blanco cosechados en primavera, que revelan una o varias flores " +
                        "ocultas al ser infusionadas.",
                "Floreciente",
                382.16,
                10,
                R.drawable.blanco
            ),
            Product(
                4,
                "Té Floreciente Pineapple",
                "Nuestra Doble Felicidad de Piña ha sido entrelazada a mano " +
                        "delicadamente.",
                "Un té verde con flores magistralmente atadas a mano para deleitar " +
                        "todos sus sentidos. Una vez que la bola de té se coloca en agua recién " +
                        "hervida, el té “florece” directamente en su tetera o vaso como una flor. " +
                        "La hermosa flor se despliega y libera un fino té verde adornado con dos " +
                        "torres de flores, comenzando con un centro amarillo, un clavel rosa y un " +
                        "jazmín. Su sabor está lleno de dulce sabor a piña y delicado té verde jazmín.",
                "Floreciente",
                126.48,
                10, // Asumiendo un stock ejemplo
                R.drawable.pineapple
            ),
            Product(
                5,
                "Té Floreciente Mango",
                "Experimente el arte genuino y minucioso del té floreciente.",
                "Nuestro té floreciente de mango crea un verdadero ramo de flores. " +
                        "Cada té está atado a mano con una flor de amaranto y lleno del aroma " +
                        "afrutado del mango. Este té lleva el espíritu relajante del jardín.",
                "Floreciente",
                681.06,
                10, // Asumiendo un stock ejemplo
                R.drawable.mango
            ),
            Product(
                6,
                "Fitelym Pro",
                "Infusión soluble compuesta por una selección de hierbas y frutos.",
                "Preparada con el fin de dirigir al cuerpo hacia una desintoxicación" +
                        " y limpieza, a través de la depuración del colon y la eliminación de " +
                        "toxinas del organismo, retirando residuos almacenados en nuestro " +
                        "intestino, además de acelerar la pérdida de grasa.",
                "Soluble",
                529.88,
                10, // Asumiendo un stock ejemplo
                R.drawable.fitelymsoluble
            ),
            Product(
                7,
                "Negro Frutos Rojos",
                "El té soluble negro con frutos rojos es una forma divertida y " +
                        "refrescante de mantenerte siempre hidratado.",
                "Se prepara en cómodos sticks monodosis que siempre podrás llevar " +
                        "contigo y añadir a una botella de agua que pidas en una terraza para " +
                        "refrescarte, o en un vaso que tengas en la oficina. No hay una forma más" +
                        " cómoda y sabrosa de darle sabor a la vida sin tener que recurrir a las " +
                        "bebidas con gas o con azúcar. Ni azúcar, ni calorías, el complemento " +
                        "perfecto para ti. Además, en verano, ¡está delicioso con hielo!",
                "Soluble",
                170.11,
                10, // Asumiendo un stock ejemplo
                R.drawable.frojos
            ),
            Product(
                8,
                "Rojo Naranja",
                "Un té soluble sin calorías y sin azúcar.",
                "El Té Soluble Rojo Naranja es ideal para todos lo que andamos " +
                        "buscando disfrutar de una bebida sana y refrescante sin azúcares o gas." +
                        " Cada caja contiene 12 cómodos sticks individuales que podrás llevar" +
                        " a cualquier parte, en el bolso, en la cartera para disfrutar en cualquier" +
                        " lugar. Solo necesitas agua, hielo en verano y en pocos segundos podrás " +
                        "disfrutar del delicioso sabor de té rojo con naranja.",
                "Soluble",
                170.11,
                10, // Asumiendo un stock ejemplo
                R.drawable.rojonaranjasoluble
            ),
            Product(
                9,
                "Verde Manzana",
                "Si estás buscando una bebida refrescante y sin azúcar y sin calorías," +
                        " la acabas de encontrar.",
                "El Té Soluble Verde Manzana es una maravilla. Sustituye las bebidas" +
                        " azucaradas y con gas por estos cómodos sticks monodosis que podrás" +
                        " disolver en agua allá donde estés.",
                "Soluble",
                170.11,
                10, // Asumiendo un stock ejemplo
                R.drawable.verdemanzanasoluble
            ),
            Product(
                10,
                "Matcha Limón",
                "Refrescante, saludable, sin azúcar y sin calorías.",
                "El té soluble matcha limón es perfecto para los que buscan una bebida" +
                        " saludable, rica y sin calorías. Una alternativa perfecta a los refrescos" +
                        " azucarados y con gas.",
                "Soluble",
                170.11,
                10, // Asumiendo un stock ejemplo
                R.drawable.matcha
            ),
            Product(
                11,
                "Jazmín",
                "Sobres de auténtico Té verde chino aromatizado.",
                "Con un exótico sabor y aroma a jazmín, ideal para degustar acompañando" +
                        " un curry o comida cantonesa. Tiene un alto contenido de antioxidantes y" +
                        " poco estimulante; con propiedades antibacteriales y desintoxicantes que " +
                        "ayudan a relajar el sistema nervioso.",
                "Bolsa",
                32.00,
                10, // Asumiendo un stock ejemplo
                R.drawable.jazminbolsa
            ),
            Product(
                12,
                "Menta Fresh",
                "Sobres de refrescante Té verde.",
                "Perfecto para preparar la típica bebida marroquí que se originó" +
                        " cuando el mandarín chino regaló al gobernante de Marruecos Té verde tipo " +
                        "'Gunpowder'. Mezcla de hojas de menta, té verde y sabor a menta.",
                "Bolsa",
                32.00,
                10, // Asumiendo un stock ejemplo
                R.drawable.mentabolsa
            ),
            Product(
                13,
                "Té Negro Arabic",
                "Puedes consumirlo en cualquier momento.",
                "Esta cosechado a mano y combinado con aceite de bergamota de" +
                        " Calabria, Italia - Cuenta con ingredientes naturales - Certificación Kosher",
                "Bolsa",
                52.00,
                10, // Asumiendo un stock ejemplo
                R.drawable.negrobolsa
            ),
            Product(
                14,
                "Hierbabuena",
                "Disfruta de una bebida caliente tradicional como lo es el té.",
                "El té de hierbabuena sin cafeína en caja. Es muy fácil de preparar," +
                        " solo añade un sobrecito en una taza con agua caliente y deja reposar unos" +
                        " minutos, si deseas un toque dulce agrega un poco de azúcar, miel o" +
                        " incluso edulcorante para mesa.",
                "Bolsa",
                44.00,
                10, // Asumiendo un stock ejemplo
                R.drawable.hierbabuenabolsita
            ),
            Product(
                15,
                "Piña",
                "El té verde, el té blanco, la hierba de limón y la menta respaldan" +
                        " la piña afrutada para crear una experiencia reparadora en una taza de té",
                "El té de piña de Tadin resalta con notas de piña, menta y hierba de" +
                        " limón para un sabor tropical equilibrado. Servido caliente o frío, este" +
                        " té es perfecto para usar durante todo el año.",
                "Bolsa",
                33.00,
                10, // Asumiendo un stock ejemplo
                R.drawable.pineapplebolsa
            )
        )
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tienda, container, false)
        val gridLayout: GridLayout = view.findViewById(R.id.gridLayoutProductos)
        val productos = getProducts()

        for (producto in productos) {
            val itemView = inflater.inflate(R.layout.item_producto, gridLayout, false)
            val imageView: ImageView = itemView.findViewById(R.id.imageProducto)
            val textView: TextView = itemView.findViewById(R.id.textProductoNombre)

            imageView.setImageResource(producto.imageResId)
            textView.text = producto.name

            itemView.setOnClickListener {
                openProductDetails(producto)
            }

            gridLayout.addView(itemView)
        }

        return view
    }

    private fun openProductDetails(producto: Product) {
        val productoFragment = Producto().apply {
            arguments = Bundle().apply {
                putInt("productId", producto.id)
                putString("productName", producto.name)
                putString("productDescription", producto.longDescription)
                putDouble("productPrice", producto.price)
                putString("productCategory", producto.category)
                putInt("productStock", producto.stock)
                putInt("productImageResId", producto.imageResId)
            }
        }
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, productoFragment)
            .addToBackStack(null)
            .commit()
    }
}
