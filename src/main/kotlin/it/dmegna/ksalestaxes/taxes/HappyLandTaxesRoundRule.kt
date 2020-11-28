package it.dmegna.ksalestaxes.taxes

import it.dmegna.ksalestaxes.taxes.data.TaxAmount
import kotlin.math.ceil

class HappyLandTaxesRoundRule : TaxesRoundRule {

    override fun round(taxAmount: TaxAmount): TaxAmount {
        val rounded = (ceil(taxAmount.value / 0.05) * 0.05)
        return TaxAmount.of(rounded)
    }

}
