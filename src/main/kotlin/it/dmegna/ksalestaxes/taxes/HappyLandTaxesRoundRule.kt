package it.dmegna.ksalestaxes.taxes

import it.dmegna.ksalestaxes.taxes.data.TaxAmount
import kotlin.math.roundToInt

class HappyLandTaxesRoundRule : TaxesRoundRule {

    override fun round(taxAmount: TaxAmount): TaxAmount {
        val rounded = ((taxAmount.value * 20.0).roundToInt()) / 20.0
        return TaxAmount(rounded)
    }

}
