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
        val isImported = isImported(description)
        return when (normalize(description)) {
            in bookDescriptions -> Book(description, isImported)
            in foodDescriptions -> FoodProduct(description, isImported)
            in medicalDescriptions -> MedicalProduct(description, isImported)
            else -> GenericProduct(description, isImported)
        }
    }

    private fun isImported(description: String) = description.contains(IMPORTED_PRODUCT_LABEL)

    private fun normalize(description: String): String {
        return description
            .split(" ")
            .filter { it != IMPORTED_PRODUCT_LABEL }
            .joinToString(" ")
    }

    companion object {
        private const val IMPORTED_PRODUCT_LABEL = "imported"
    }

}