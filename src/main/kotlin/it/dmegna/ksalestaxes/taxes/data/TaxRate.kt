package it.dmegna.ksalestaxes.taxes.data

import it.dmegna.ksalestaxes.taxes.data.DataUtils.Companion.normalize

data class TaxRate(val value: Double) {
    operator fun plus(taxRate: TaxRate) = TaxRate(normalize(this.value + taxRate.value))
}
