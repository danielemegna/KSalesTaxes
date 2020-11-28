package it.dmegna.ksalestaxes.taxes.data

data class NetPrice(val value: Double) {
    operator fun times(taxRate: TaxRate) = TaxAmount(this.value * taxRate.value)
    operator fun plus(taxAmount: TaxAmount) = TaxedPrice(this.value + taxAmount.value)
}
