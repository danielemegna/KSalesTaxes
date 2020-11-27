package it.dmegna.ksalestaxes

class ShoppingBasket {

    val items = mutableListOf<Item>()

    fun add(qty: Int, description: String, unitNetPrice: Double) {
        items.add(Item(qty, description, unitNetPrice))
    }

    data class Item(val qty: Int, val description: String, val unitNetPrice: Double)
}