package it.dmegna.ksalestaxes.shoppingbasket.taxes

import it.dmegna.ksalestaxes.shoppingbasket.taxes.amounts.NetPrice
import it.dmegna.ksalestaxes.shoppingbasket.taxes.amounts.TaxAmount
import it.dmegna.ksalestaxes.shoppingbasket.taxes.amounts.TaxRate

class TaxAmountCalculator(private val taxesRoundRule: TaxesRoundRule) {

    fun getFor(taxRate: TaxRate, netPrice: NetPrice): TaxAmount {
        val taxAmount = netPrice * taxRate
        return taxesRoundRule.round(taxAmount)
    }

}

