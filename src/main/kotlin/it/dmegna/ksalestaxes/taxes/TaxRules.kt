package it.dmegna.ksalestaxes.taxes

import it.dmegna.ksalestaxes.products.Product
import it.dmegna.ksalestaxes.taxes.data.TaxRate

interface TaxRules {
    fun getTaxRateFor(product: Product): TaxRate

}
