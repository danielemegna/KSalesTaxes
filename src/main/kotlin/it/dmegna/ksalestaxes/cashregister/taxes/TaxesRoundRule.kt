package it.dmegna.ksalestaxes.cashregister.taxes

import it.dmegna.ksalestaxes.cashregister.taxes.amounts.TaxAmount

interface TaxesRoundRule {
    fun round(taxAmount: TaxAmount): TaxAmount
}