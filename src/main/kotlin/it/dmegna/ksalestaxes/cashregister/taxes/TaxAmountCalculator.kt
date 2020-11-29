package it.dmegna.ksalestaxes.cashregister.taxes

import it.dmegna.ksalestaxes.cashregister.taxes.data.NetPrice
import it.dmegna.ksalestaxes.cashregister.taxes.data.TaxAmount
import it.dmegna.ksalestaxes.cashregister.taxes.data.TaxRate

class TaxAmountCalculator(private val taxesRoundRule: TaxesRoundRule) {
    fun getFor(taxRate: TaxRate, netPrice: NetPrice): TaxAmount {
        val taxAmount = netPrice * taxRate
        return taxesRoundRule.round(taxAmount)
    }
}

