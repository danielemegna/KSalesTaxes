package it.dmegna.ksalestaxes.taxes

import it.dmegna.ksalestaxes.products.*
import it.dmegna.ksalestaxes.taxes.data.TaxRate

class HappyLandTaxRules : TaxRules {
    override fun getTaxRateFor(product: Product): TaxRate {
        val baseTaxRate = getBaseTaxRateFor(product)

        if (product.isImported)
            return baseTaxRate + TaxRate(0.05)

        return baseTaxRate
    }

    private fun getBaseTaxRateFor(product: Product): TaxRate {
        return when (product) {
            is GenericProduct -> TaxRate(0.10)
            is Book, is FoodProduct, is MedicalProduct -> TaxRate(0.0)
        }
    }
}
