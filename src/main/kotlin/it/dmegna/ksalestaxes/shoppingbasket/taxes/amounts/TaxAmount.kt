package it.dmegna.ksalestaxes.shoppingbasket.taxes.amounts

data class TaxAmount private constructor(val value: Double) {
    operator fun times(n: Int): TaxAmount = of(value * n)
    operator fun plus(taxAmount: TaxAmount): TaxAmount = of(value + taxAmount.value)

    companion object {
        fun of(value: Double) = TaxAmount(AmountsUtil.normalize(value))
    }
}
