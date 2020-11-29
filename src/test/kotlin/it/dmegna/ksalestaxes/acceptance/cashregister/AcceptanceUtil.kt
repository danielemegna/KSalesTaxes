package it.dmegna.ksalestaxes.acceptance.cashregister

import it.dmegna.ksalestaxes.cashregister.CashRegister
import it.dmegna.ksalestaxes.cashregister.products.HappyLandProductFactory
import it.dmegna.ksalestaxes.cashregister.taxes.HappyLandTaxRules
import it.dmegna.ksalestaxes.cashregister.taxes.HappyLandTaxesRoundRule
import it.dmegna.ksalestaxes.cashregister.taxes.TaxAmountCalculator

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