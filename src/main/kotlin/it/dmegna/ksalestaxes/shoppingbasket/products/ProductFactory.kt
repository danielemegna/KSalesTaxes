package it.dmegna.ksalestaxes.shoppingbasket.products

typealias RawDescription = String

interface ProductFactory {
    fun from(rawDescription: RawDescription): Product
}