package it.dmegna.ksalestaxes.cashregister.taxes

import it.dmegna.ksalestaxes.cashregister.products.Product
import it.dmegna.ksalestaxes.cashregister.taxes.data.TaxRate

interface TaxRules {
    fun getTaxRateFor(product: Product): TaxRate

}
