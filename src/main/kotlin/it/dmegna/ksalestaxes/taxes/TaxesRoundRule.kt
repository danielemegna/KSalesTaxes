package it.dmegna.ksalestaxes.taxes

import it.dmegna.ksalestaxes.taxes.data.TaxAmount

interface TaxesRoundRule {
    fun round(taxAmount: TaxAmount): TaxAmount
}