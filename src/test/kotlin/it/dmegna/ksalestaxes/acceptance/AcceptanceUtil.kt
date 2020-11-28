package it.dmegna.ksalestaxes.acceptance

import it.dmegna.ksalestaxes.CashRegister
import it.dmegna.ksalestaxes.products.ProductFactory
import it.dmegna.ksalestaxes.taxes.HappyLandTaxRules
import it.dmegna.ksalestaxes.taxes.HappyLandTaxesRoundRule
import it.dmegna.ksalestaxes.taxes.TaxAmountCalculator

class AcceptanceUtil {
    companion object {
        fun buildHappyLandCashRegister(): CashRegister {
            return CashRegister(
                productFactory = ProductFactory(),
                taxRules = HappyLandTaxRules(),
                taxAmountCalculator = TaxAmountCalculator(
                    taxesRoundRule = HappyLandTaxesRoundRule()
                )
            )
        }
    }
}