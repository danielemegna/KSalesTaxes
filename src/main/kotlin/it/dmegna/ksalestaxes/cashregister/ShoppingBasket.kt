package it.dmegna.ksalestaxes.cashregister

data class ShoppingBasket(val items: List<Item>) {
    data class Item(val qty: Int, val description: String, val unitNetPrice: Double)
}