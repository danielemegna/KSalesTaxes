package it.dmegna.ksalestaxes

import it.dmegna.ksalestaxes.products.ProductFactory
import it.dmegna.ksalestaxes.taxes.TaxAmountCalculator
import it.dmegna.ksalestaxes.taxes.TaxRules
import it.dmegna.ksalestaxes.taxes.data.NetPrice
import it.dmegna.ksalestaxes.taxes.data.TaxAmount
import it.dmegna.ksalestaxes.taxes.data.TaxedPrice

class CashRegister(
    private val productFactory: ProductFactory,
    private val taxRules: TaxRules,
    private val taxAmountCalculator: TaxAmountCalculator
) {
    fun receiptFor(shoppingBasket: ShoppingBasket): Receipt {
        val items = mutableListOf<Receipt.Item>()
        var totalTaxAmount = TaxAmount.of(0.0)
        var totalTaxedPrice = TaxedPrice.of(0.0)

        shoppingBasket.items.forEach {
            val product = productFactory.from(it.description)
            val taxRule = taxRules.getTaxRateFor(product)
            val unitNetPrice = NetPrice.of(it.unitNetPrice)

            val unitTaxAmount = taxAmountCalculator.getFor(taxRule, unitNetPrice)
            val unitTaxedPrice = unitNetPrice + unitTaxAmount

            val taxedPrice = unitTaxedPrice * it.qty
            val taxAmount = unitTaxAmount * it.qty

            val receiptItem = Receipt.Item(it.qty, it.description, taxedPrice.value)
            items.add(receiptItem)

            totalTaxAmount += taxAmount
            totalTaxedPrice += taxedPrice
        }

        return Receipt(items, totalTaxAmount.value, totalTaxedPrice.value)
    }

}
