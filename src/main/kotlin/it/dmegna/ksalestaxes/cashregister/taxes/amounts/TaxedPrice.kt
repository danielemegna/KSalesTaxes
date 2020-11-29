package it.dmegna.ksalestaxes.cashregister.taxes.amounts

data class TaxedPrice private constructor(val value: Double) {
    operator fun times(n: Int): TaxedPrice = of(value * n)
    operator fun plus(taxedPrice: TaxedPrice): TaxedPrice = of(value + taxedPrice.value)

    companion object {
        fun of(value: Double) = TaxedPrice(AmountsUtil.normalize(value))
    }
}