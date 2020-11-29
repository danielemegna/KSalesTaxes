package it.dmegna.ksalestaxes.cashregister.taxes.amounts

data class NetPrice private constructor(val value: Double) {
    operator fun times(taxRate: TaxRate) = TaxAmount.of(value * taxRate.value)
    operator fun plus(taxAmount: TaxAmount) = TaxedPrice.of(value + taxAmount.value)

    companion object {
        fun of(value: Double) = NetPrice(AmountsUtil.normalize(value))
    }
}
