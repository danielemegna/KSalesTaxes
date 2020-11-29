package it.dmegna.ksalestaxes.shoppingbasket.products

interface ProductFactory {
    fun from(rawDescription: String): Product
}