package it.dmegna.ksalestaxes.shoppingbasket.taxes.amounts

data class TaxRate private constructor(val value: Double) {
    operator fun plus(taxRate: TaxRate): TaxRate = of(value + taxRate.value)

    companion object {
        fun of(value: Double) = TaxRate(AmountsUtil.normalize(value))
    }
}
