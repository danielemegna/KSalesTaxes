package it.dmegna.ksalestaxes.cashregister

interface ShoppingBasketProcessor {
    fun process(shoppingBasket: ShoppingBasket): Receipt
}
