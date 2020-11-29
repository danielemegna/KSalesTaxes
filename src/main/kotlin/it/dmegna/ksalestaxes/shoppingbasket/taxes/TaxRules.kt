package it.dmegna.ksalestaxes.shoppingbasket.taxes

import it.dmegna.ksalestaxes.shoppingbasket.products.Product
import it.dmegna.ksalestaxes.shoppingbasket.taxes.amounts.TaxRate

interface TaxRules {
    fun getTaxRateFor(product: Product): TaxRate
}
