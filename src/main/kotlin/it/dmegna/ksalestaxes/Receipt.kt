package it.dmegna.ksalestaxes

data class Receipt constructor(
    val items: List<Item>,
    val salesTaxes: Double
) {
    data class Item(val qty: Int, val description: String, val taxedTotalPrice: Double)
}

