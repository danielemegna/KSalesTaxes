package it.dmegna.ksalestaxes.taxes

import it.dmegna.ksalestaxes.products.*
import it.dmegna.ksalestaxes.taxes.data.TaxRate

class HappyLandTaxRules : TaxRules {
    override fun getTaxRateFor(product: Product): TaxRate {
        val baseTaxRate = getBaseTaxRateFor(product)

        if (product.isImported)
            return baseTaxRate + TaxRate.of(0.05)

        return baseTaxRate
    }

    private fun getBaseTaxRateFor(product: Product): TaxRate {
        return when (product) {
            is GenericProduct -> TaxRate.of(0.10)
            is Book, is FoodProduct, is MedicalProduct -> TaxRate.of(0.0)
        }
    }
}
