package it.dmegna.ksalestaxes.shoppingbasket

import it.dmegna.ksalestaxes.shoppingbasket.inbound.ShoppingBasket
import it.dmegna.ksalestaxes.shoppingbasket.outbound.Receipt

interface ShoppingBasketProcessor {
    fun process(shoppingBasket: ShoppingBasket): Receipt
}
