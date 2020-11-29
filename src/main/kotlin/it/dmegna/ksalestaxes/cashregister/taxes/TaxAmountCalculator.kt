package it.dmegna.ksalestaxes.cashregister.taxes

import it.dmegna.ksalestaxes.cashregister.taxes.amounts.NetPrice
import it.dmegna.ksalestaxes.cashregister.taxes.amounts.TaxAmount
import it.dmegna.ksalestaxes.cashregister.taxes.amounts.TaxRate

class TaxAmountCalculator(private val taxesRoundRule: TaxesRoundRule) {

    fun getFor(taxRate: TaxRate, netPrice: NetPrice): TaxAmount {
        val taxAmount = netPrice * taxRate
        return taxesRoundRule.round(taxAmount)
    }

}

