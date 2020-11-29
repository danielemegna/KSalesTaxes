package it.dmegna.ksalestaxes.shoppingbasket.taxes

import it.dmegna.ksalestaxes.shoppingbasket.taxes.amounts.TaxAmount
import kotlin.math.ceil

class HappyLandTaxesRoundRule : TaxesRoundRule {

    override fun round(taxAmount: TaxAmount): TaxAmount {
        val rounded = (ceil(taxAmount.value / 0.05) * 0.05)
        return TaxAmount.of(rounded)
    }

}
