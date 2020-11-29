package it.dmegna.ksalestaxes.cashregister.products

interface ProductFactory {
    fun from(rawDescription: String): Product
}