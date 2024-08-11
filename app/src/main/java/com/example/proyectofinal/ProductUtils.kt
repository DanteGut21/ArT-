package com.example.proyectofinal

//data class Product(
//    val id: Int,
//    val name: String,
//    val shortDescription: String?,
//    val longDescription: String?,
//    val category: String?,
//    val price: Double,
//    val stock: Int,
//    val imageResId: String
//)

//fun getProducts(): List<Product> {
//    return listOf(
//        Product(
//            1,
//            "Dragon Ball",
//            "Té Floreciente",
//            "Como equilibrista en la cuerda floja, aparece un crisantemo rosa " +
//                    "suspendido en un arco de jazmines. Una flor de caléndula espera ansiosa " +
//                    "recibirlo por si no puede evitar caer. Cada perla está formada por hojas de" +
//                    " Té atadas a mano con algodón, con flores naturales ocultas en su interior.",
//            "Floreciente",
//            100.0,
//            10,
//            R.drawable.dragonball
//        ),
//        Product(
//            2,
//            "Té Floreciente Jazmín",
//            "La flor del jazmín tiene propiedades antioxidantes, " +
//                    "antienvejecimiento y para reducir el estrés.",
//            "Hecho a mano enrollando las hojas con flores de jazmín en orbes de " +
//                    "loto, un sabor dulce y refrescante de cuerpo completo, y un agradable " +
//                    "retrogusto floral de larga duración, lleno de antioxidantes para aliviar el" +
//                    " estrés y vigorizar la juventud.",
//            "Floreciente",
//            409.25,
//            5,
//            R.drawable.jazmin
//        ),
//        Product(
//            3,
//            "Té Floreciente Blanco",
//            "Cada bolita puede ser infusionada hasta 3 veces, proporcionando un " +
//                    "total de al menos 1 litro de té, cada porción es un manojo de finos brotes " +
//                    "de té blanco cosechados en primavera, que revelan una o varias flores " +
//                    "ocultas al ser infusionadas.",
//            "Floreciente",
//            382.16,
//            10,
//            R.drawable.blanco
//        ),
//        Product(
//            4,
//            "Té Floreciente Pineapple",
//            "Un té verde con flores magistralmente atadas a mano para deleitar " +
//                    "todos sus sentidos. Una vez que la bola de té se coloca en agua recién " +
//                    "hervida, el té “florece” directamente en su tetera o vaso como una flor. " +
//                    "La hermosa flor se despliega y libera un fino té verde adornado con dos " +
//                    "torres de flores, comenzando con un centro amarillo, un clavel rosa y un " +
//                    "jazmín. Su sabor está lleno de dulce sabor a piña y delicado té verde jazmín.",
//            "Floreciente",
//            126.48,
//            10, // Asumiendo un stock ejemplo
//            R.drawable.pineapple
//        ),
//        Product(
//            5,
//            "Té Floreciente Mango",
//            "Nuestro té floreciente de mango crea un verdadero ramo de flores. " +
//                    "Cada té está atado a mano con una flor de amaranto y lleno del aroma " +
//                    "afrutado del mango. Este té lleva el espíritu relajante del jardín.",
//            "Floreciente",
//            681.06,
//            10, // Asumiendo un stock ejemplo
//            R.drawable.mango
//        ),
//        Product(
//            6,
//            "Fitelym Pro",
//            "Preparada con el fin de dirigir al cuerpo hacia una desintoxicación" +
//                    " y limpieza, a través de la depuración del colon y la eliminación de " +
//                    "toxinas del organismo, retirando residuos almacenados en nuestro " +
//                    "intestino, además de acelerar la pérdida de grasa.",
//            "Soluble",
//            529.88,
//            10, // Asumiendo un stock ejemplo
//            R.drawable.fitelymsoluble
//        ),
//        Product(
//            7,
//            "Negro Frutos Rojos",
//            "Se prepara en cómodos sticks monodosis que siempre podrás llevar " +
//                    "contigo y añadir a una botella de agua que pidas en una terraza para " +
//                    "refrescarte, o en un vaso que tengas en la oficina. No hay una forma más" +
//                    " cómoda y sabrosa de darle sabor a la vida sin tener que recurrir a las " +
//                    "bebidas con gas o con azúcar. Ni azúcar, ni calorías, el complemento " +
//                    "perfecto para ti. Además, en verano, ¡está delicioso con hielo!",
//            "Soluble",
//            170.11,
//            10, // Asumiendo un stock ejemplo
//            R.drawable.frojos
//        ),
//        Product(
//            8,
//            "Rojo Naranja",
//            "El Té Soluble Rojo Naranja es ideal para todos lo que andamos " +
//                    "buscando disfrutar de una bebida sana y refrescante sin azúcares o gas." +
//                    " Cada caja contiene 12 cómodos sticks individuales que podrás llevar" +
//                    " a cualquier parte, en el bolso, en la cartera para disfrutar en cualquier" +
//                    " lugar. Solo necesitas agua, hielo en verano y en pocos segundos podrás " +
//                    "disfrutar del delicioso sabor de té rojo con naranja.",
//            "Soluble",
//            170.11,
//            10, // Asumiendo un stock ejemplo
//            R.drawable.rojonaranjasoluble
//        ),
//        Product(
//            9,
//            "Verde Manzana",
//            "El Té Soluble Verde Manzana es una maravilla. Sustituye las bebidas" +
//                    " azucaradas y con gas por estos cómodos sticks monodosis que podrás" +
//                    " disolver en agua allá donde estés.",
//            "Soluble",
//            170.11,
//            10, // Asumiendo un stock ejemplo
//            R.drawable.verdemanzanasoluble
//        ),
//        Product(
//            10,
//            "Matcha Limón",
//            "El té soluble matcha limón es perfecto para los que buscan una bebida" +
//                    " saludable, rica y sin calorías. Una alternativa perfecta a los refrescos" +
//                    " azucarados y con gas.",
//            "Soluble",
//            170.11,
//            10, // Asumiendo un stock ejemplo
//            R.drawable.matcha
//        ),
//        Product(
//            11,
//            "Jazmín",
//            "Con un exótico sabor y aroma a jazmín, ideal para degustar acompañando" +
//                    " un curry o comida cantonesa. Tiene un alto contenido de antioxidantes y" +
//                    " poco estimulante; con propiedades antibacteriales y desintoxicantes que " +
//                    "ayudan a relajar el sistema nervioso.",
//            "Bolsa",
//            32.00,
//            10, // Asumiendo un stock ejemplo
//            R.drawable.jazminbolsa
//        ),
//        Product(
//            12,
//            "Menta Fresh",
//            "Perfecto para preparar la típica bebida marroquí que se originó" +
//                    " cuando el mandarín chino regaló al gobernante de Marruecos Té verde tipo " +
//                    "'Gunpowder'. Mezcla de hojas de menta, té verde y sabor a menta.",
//            "Bolsa",
//            32.00,
//            10, // Asumiendo un stock ejemplo
//            R.drawable.mentabolsa
//        ),
//        Product(
//            13,
//            "Té Negro Arabic",
//            "Esta cosechado a mano y combinado con aceite de bergamota de" +
//                    " Calabria, Italia - Cuenta con ingredientes naturales - Certificación Kosher",
//            "Bolsa",
//            52.00,
//            10, // Asumiendo un stock ejemplo
//            R.drawable.negrobolsa
//        ),
//        Product(
//            14,
//            "Hierbabuena",
//            "El té de hierbabuena sin cafeína en caja. Es muy fácil de preparar," +
//                    " solo añade un sobrecito en una taza con agua caliente y deja reposar unos" +
//                    " minutos, si deseas un toque dulce agrega un poco de azúcar, miel o" +
//                    " incluso edulcorante para mesa.",
//            "Bolsa",
//            44.00,
//            10, // Asumiendo un stock ejemplo
//            R.drawable.hierbabuenabolsita
//        ),
//        Product(
//            15,
//            "Piña",
//            "El té de piña de Tadin resalta con notas de piña, menta y hierba de" +
//                    " limón para un sabor tropical equilibrado. Servido caliente o frío, este" +
//                    " té es perfecto para usar durante todo el año.",
//            "Bolsa",
//            33.00,
//            10, // Asumiendo un stock ejemplo
//            R.drawable.pineapplebolsa
//        )
//    )
//}