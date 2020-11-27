package it.dmegna.ksalestaxes

data class Receipt constructor(
    val items: List<Item>,
    val salesTaxes: Double
) {
    fun getTotal() = items.sumByDouble { it.taxedTotalPrice }

    data class Item(val qty: Int, val description: String, val taxedTotalPrice: Double)
}

