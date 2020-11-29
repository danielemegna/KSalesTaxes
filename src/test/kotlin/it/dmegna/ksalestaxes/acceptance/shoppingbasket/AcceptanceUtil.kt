package it.dmegna.ksalestaxes.acceptance.shoppingbasket

import it.dmegna.ksalestaxes.shoppingbasket.CashRegister
import it.dmegna.ksalestaxes.shoppingbasket.products.HappyLandProductFactory
import it.dmegna.ksalestaxes.shoppingbasket.taxes.HappyLandTaxRules
import it.dmegna.ksalestaxes.shoppingbasket.taxes.HappyLandTaxesRoundRule
import it.dmegna.ksalestaxes.shoppingbasket.taxes.TaxAmountCalculator

class AcceptanceUtil {
    companion object {
        fun buildHappyLandCashRegister(): CashRegister {
            return CashRegister(
                productFactory = HappyLandProductFactory(),
                taxRules = HappyLandTaxRules(),
                taxAmountCalculator = TaxAmountCalculator(
                    taxesRoundRule = HappyLandTaxesRoundRule()
                )
            )
        }
    }
}