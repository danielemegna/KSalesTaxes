package it.dmegna.ksalestaxes

import it.dmegna.ksalestaxes.products.ProductFactory
import it.dmegna.ksalestaxes.taxes.HappyLandTaxRules
import it.dmegna.ksalestaxes.taxes.HappyLandTaxesRoundRule
import it.dmegna.ksalestaxes.taxes.TaxAmountCalculator
import it.dmegna.ksalestaxes.taxes.data.NetPrice

class CashRegister {

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
        val product = ProductFactory().from(it.description)
        val taxRate = HappyLandTaxRules().getTaxRateFor(product)
        val taxAmount = TaxAmountCalculator(HappyLandTaxesRoundRule()).getFor(taxRate, NetPrice(it.unitNetPrice))
        return taxAmount.value
    }

}
