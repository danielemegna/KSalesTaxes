package it.dmegna.ksalestaxes

class ShoppingBasket {

    val items = mutableListOf<Item>()

    fun add(qty: Int, description: String, unitPrice: Double) {
        items.add(Item(qty, description, unitPrice))
    }

    data class Item(val qty: Int, val description: String, val unitPrice: Double)
}