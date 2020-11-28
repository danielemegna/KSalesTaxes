package it.dmegna.ksalestaxes.taxes.data

class TaxedPrice private constructor(val value: Double) {
    companion object {
        fun normalized(value: Double): TaxedPrice {
            return TaxedPrice("%.2f".format(value).toDouble())
        }
    }

    operator fun times(n: Int) = TaxedPrice(value * n)
}