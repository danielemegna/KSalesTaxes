package it.dmegna.ksalestaxes.taxes

import it.dmegna.ksalestaxes.taxes.data.TaxAmount
import kotlin.math.round as mathRound

class HappyLandTaxesRoundRule : TaxesRoundRule {

    override fun round(taxAmount: TaxAmount): TaxAmount {
        val rounded = mathRound(taxAmount.value * 20.0) / 20
        return TaxAmount(rounded)
    }

}
