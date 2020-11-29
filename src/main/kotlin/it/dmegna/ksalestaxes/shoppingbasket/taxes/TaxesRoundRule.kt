package it.dmegna.ksalestaxes.shoppingbasket.taxes

import it.dmegna.ksalestaxes.shoppingbasket.taxes.amounts.TaxAmount

interface TaxesRoundRule {
    fun round(taxAmount: TaxAmount): TaxAmount
}