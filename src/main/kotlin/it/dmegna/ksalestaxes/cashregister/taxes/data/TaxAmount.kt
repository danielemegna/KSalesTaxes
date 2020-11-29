package it.dmegna.ksalestaxes.cashregister.taxes.data

data class TaxAmount private constructor(val value: Double) {
    operator fun times(n: Int): TaxAmount = of(value * n)
    operator fun plus(taxAmount: TaxAmount): TaxAmount = of(value + taxAmount.value)

    companion object {
        fun of(value: Double) = TaxAmount(DoubleUtil.normalize(value))
    }
}
