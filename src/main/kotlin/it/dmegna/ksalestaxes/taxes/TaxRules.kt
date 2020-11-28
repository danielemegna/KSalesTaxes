package it.dmegna.ksalestaxes.taxes

import it.dmegna.ksalestaxes.products.Product

interface TaxRules {
    fun getTaxRateFor(product: Product): TaxRate

}
