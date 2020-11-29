package it.dmegna.ksalestaxes.shoppingbasket.inbound

data class ShoppingBasket(val items: List<Item>) {
    data class Item(val qty: Int, val description: String, val unitNetPrice: Double)
}