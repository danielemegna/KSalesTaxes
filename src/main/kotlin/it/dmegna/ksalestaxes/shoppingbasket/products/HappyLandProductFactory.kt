package it.dmegna.ksalestaxes.shoppingbasket.products

class HappyLandProductFactory : ProductFactory {

    override fun from(rawDescription: String): Product {
        val normalizedDescription = normalize(rawDescription)
        val isImported = isImported(rawDescription)
        val description = descriptionFor(normalizedDescription, isImported)
        return when (normalizedDescription) {
            in bookDescriptions -> Book(description, isImported)
            in foodDescriptions -> FoodProduct(description, isImported)
            in medicalDescriptions -> MedicalProduct(description, isImported)
            else -> GenericProduct(description, isImported)
        }
    }

    private fun normalize(description: String): String {
        return description
            .split(" ")
            .filter { it != IMPORTED_PRODUCT_LABEL }
            .joinToString(" ")
    }

    private fun isImported(description: String) = description.contains(IMPORTED_PRODUCT_LABEL)

    private fun descriptionFor(normalizedDescription: String, isImported: Boolean): String {
        return if (!isImported) normalizedDescription
        else "$IMPORTED_PRODUCT_LABEL $normalizedDescription"
    }

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

    companion object {
        private const val IMPORTED_PRODUCT_LABEL = "imported"
    }

}