package it.dmegna.ksalestaxes.taxes.data

import it.dmegna.ksalestaxes.taxes.data.DataUtils.Companion.normalize

class TaxedPrice private constructor(val value: Double) {
    companion object {
        fun normalized(value: Double): TaxedPrice {
            return TaxedPrice(normalize(value))
        }
    }

    operator fun times(n: Int) = TaxedPrice(value * n)
}