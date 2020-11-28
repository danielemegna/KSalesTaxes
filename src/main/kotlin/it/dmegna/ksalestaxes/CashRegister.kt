package it.dmegna.ksalestaxes

import it.dmegna.ksalestaxes.products.ProductFactory
import it.dmegna.ksalestaxes.taxes.TaxAmountCalculator
import it.dmegna.ksalestaxes.taxes.TaxRules
import it.dmegna.ksalestaxes.taxes.data.NetPrice

class CashRegister(
    private val productFactory: ProductFactory,
    private val taxRules: TaxRules,
    private val taxAmountCalculator: TaxAmountCalculator
) {
    fun receiptFor(shoppingBasket: ShoppingBasket): Receipt {
        val items = mutableListOf<Receipt.Item>()
        var salesTaxes = 0.0

        shoppingBasket.items.forEach {
            val unitItemTaxes = calculateUnitItemTaxesFor(it)
            val taxedUnitPrice = it.unitNetPrice + unitItemTaxes
            val taxedTotalPrice = taxedUnitPrice * it.qty
            val normalized = "%.2f".format(taxedTotalPrice).toDouble()
            items.add(Receipt.Item(it.qty, it.description, normalized))
            salesTaxes += unitItemTaxes * it.qty
        }

        return Receipt(items, salesTaxes)
    }

    private fun calculateUnitItemTaxesFor(it: ShoppingBasket.Item): Double {
        val product = productFactory.from(it.description)
        val taxRate = taxRules.getTaxRateFor(product)
        val taxAmount = taxAmountCalculator.getFor(taxRate, NetPrice(it.unitNetPrice))
        return taxAmount.value
    }

}
