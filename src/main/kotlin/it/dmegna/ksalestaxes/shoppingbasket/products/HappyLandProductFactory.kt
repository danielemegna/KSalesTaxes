package it.dmegna.ksalestaxes.shoppingbasket.products

typealias CleanDescription = String

class HappyLandProductFactory : ProductFactory {

    override fun from(rawDescription: RawDescription): Product {
        val cleanDescription = rawDescription.clean()
        val isImported = rawDescription.isImported()
        val normalizedDescription = cleanDescription.normalize(isImported)
        return when (cleanDescription) {
            in bookDescriptions -> Book(normalizedDescription, isImported)
            in foodDescriptions -> FoodProduct(normalizedDescription, isImported)
            in medicalDescriptions -> MedicalProduct(normalizedDescription, isImported)
            else -> GenericProduct(normalizedDescription, isImported)
        }
    }

    private fun RawDescription.clean(): CleanDescription {
        return this
            .split(" ")
            .filter { it != IMPORTED_PRODUCT_LABEL }
            .joinToString(" ")
    }

    private fun RawDescription.isImported() = this.contains(IMPORTED_PRODUCT_LABEL)

    private fun CleanDescription.normalize(isImported: Boolean): String {
        return when (isImported) {
            true -> "$IMPORTED_PRODUCT_LABEL $this"
            false -> this
        }
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