package it.dmegna.ksalestaxes.taxes.data

data class TaxedPrice(val value: Double) {
    operator fun times(n: Int) = TaxedPrice(value * n)
}