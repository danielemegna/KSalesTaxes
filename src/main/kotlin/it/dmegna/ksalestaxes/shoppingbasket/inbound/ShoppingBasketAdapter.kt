package it.dmegna.ksalestaxes.shoppingbasket.inbound

interface ShoppingBasketAdapter<T> {
    fun from(values: Iterable<T>): ShoppingBasket
    class InvalidInputException(message: String) : IllegalArgumentException(message)
}
