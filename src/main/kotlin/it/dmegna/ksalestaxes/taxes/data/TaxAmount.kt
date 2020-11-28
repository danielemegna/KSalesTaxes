package it.dmegna.ksalestaxes.taxes.data

data class TaxAmount(val value: Double) {
    operator fun times(n: Int) = TaxAmount(this.value * n)
}
