package it.dmegna.ksalestaxes

interface ShoppingSessionInputProvider<T> {
    fun readAll(): Iterable<T>
}