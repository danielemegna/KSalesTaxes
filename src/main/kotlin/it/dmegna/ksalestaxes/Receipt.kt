package it.dmegna.ksalestaxes

data class Receipt(val items: List<Item>, val salesTaxes: Double, val total: Double) {
    data class Item(val qty: Int, val description: String, val taxedTotalPrice: Double)
}

