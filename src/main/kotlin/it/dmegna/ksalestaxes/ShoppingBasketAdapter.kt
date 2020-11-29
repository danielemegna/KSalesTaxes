package it.dmegna.ksalestaxes

import it.dmegna.ksalestaxes.cashregister.ShoppingBasket

interface ShoppingBasketAdapter<T> {
    fun from(values: Iterable<T>): ShoppingBasket
}
