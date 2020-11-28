package it.dmegna.ksalestaxes.taxes.data

data class TaxRate private constructor(val value: Double) {
    operator fun plus(taxRate: TaxRate): TaxRate = of(value + taxRate.value)

    companion object {
        fun of(value: Double) = TaxRate(DoubleUtil.normalize(value))
    }
}
