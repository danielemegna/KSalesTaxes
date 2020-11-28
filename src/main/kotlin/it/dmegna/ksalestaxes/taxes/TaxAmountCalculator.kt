package it.dmegna.ksalestaxes.taxes

import it.dmegna.ksalestaxes.taxes.data.NetPrice
import it.dmegna.ksalestaxes.taxes.data.TaxAmount
import it.dmegna.ksalestaxes.taxes.data.TaxRate

class TaxAmountCalculator(private val roundRule: TaxesRoundRule) {
    fun getFor(taxRate: TaxRate, netPrice: NetPrice): TaxAmount {
        val taxAmount = netPrice * taxRate
        return roundRule.round(taxAmount)
    }
}

private operator fun NetPrice.times(taxRate: TaxRate): TaxAmount {
    return TaxAmount(this.value * taxRate.value)
}
