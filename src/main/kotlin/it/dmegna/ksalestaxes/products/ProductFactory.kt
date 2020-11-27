package it.dmegna.ksalestaxes.products

class ProductFactory {

    private val bookDescriptions = setOf(
        "book",
        "Harry Potter and the Philosopher's Stone",
        "Clean Code: A Handbook of Agile Software Craftsmanship"
    )
    private val foodDescriptions = setOf(
        "chocolate bar",
        "box of chocolates",
        "slice of pizza"
    )

    private val medicalDescriptions = setOf(
        "packet of headache pills",
        "headache pills",
        "back pain pills"
    )

    fun from(description: String): Product {
        return when (normalize(description)) {
            in bookDescriptions -> Book(description)
            in foodDescriptions -> FoodProduct(description)
            in medicalDescriptions -> MedicalProduct(description)
            else -> GenericProduct(description)
        }
    }

    private fun normalize(description: String): String {
        return description
            .split(" ")
            .filter { it != "imported" }
            .joinToString(" ")
    }

}