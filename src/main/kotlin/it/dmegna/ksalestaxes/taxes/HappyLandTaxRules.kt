package it.dmegna.ksalestaxes.taxes

import it.dmegna.ksalestaxes.products.*

class HappyLandTaxRules : TaxRules {
    override fun getTaxRateFor(product: Product): TaxRate {
        return when (product) {
            is GenericProduct -> TaxRate(0.10)
            is Book, is FoodProduct, is MedicalProduct -> TaxRate(0.0)
        }
    }
}
