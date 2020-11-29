package it.dmegna.ksalestaxes.shoppingbasket.taxes

import it.dmegna.ksalestaxes.shoppingbasket.products.*
import it.dmegna.ksalestaxes.shoppingbasket.taxes.amounts.TaxRate

class HappyLandTaxRules : TaxRules {

    override fun getTaxRateFor(product: Product): TaxRate {
        return baseTaxRateFor(product)
            .eventuallyAddImportedTax(product)
    }

    private fun baseTaxRateFor(product: Product): TaxRate {
        return when (product) {
            is GenericProduct -> TaxRate.of(GENERIC_PRODUCT_BASE_TAX_RATE)
            is Book, is FoodProduct, is MedicalProduct -> TaxRate.zero()
        }
    }

    private fun TaxRate.eventuallyAddImportedTax(product: Product): TaxRate {
        if (!product.isImported) return this
        return this + TaxRate.of(IMPORTED_PRODUCT_ADDITIONAL_TAX_RATE)
    }

    companion object {
        private const val GENERIC_PRODUCT_BASE_TAX_RATE = 0.10
        private const val IMPORTED_PRODUCT_ADDITIONAL_TAX_RATE = 0.05
    }

}
