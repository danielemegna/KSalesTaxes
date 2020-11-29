package it.dmegna.ksalestaxes.shoppingbasket

import it.dmegna.ksalestaxes.shoppingbasket.inbound.ShoppingBasket
import it.dmegna.ksalestaxes.shoppingbasket.outbound.Receipt
import it.dmegna.ksalestaxes.shoppingbasket.products.Product
import it.dmegna.ksalestaxes.shoppingbasket.products.ProductFactory
import it.dmegna.ksalestaxes.shoppingbasket.taxes.TaxAmountCalculator
import it.dmegna.ksalestaxes.shoppingbasket.taxes.TaxRules
import it.dmegna.ksalestaxes.shoppingbasket.taxes.amounts.NetPrice
import it.dmegna.ksalestaxes.shoppingbasket.taxes.amounts.TaxAmount
import it.dmegna.ksalestaxes.shoppingbasket.taxes.amounts.TaxedPrice

class CashRegister(
    private val productFactory: ProductFactory,
    private val taxRules: TaxRules,
    private val taxAmountCalculator: TaxAmountCalculator
) : ShoppingBasketProcessor {

    override fun process(shoppingBasket: ShoppingBasket): Receipt {
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

            val receiptItemDescription = receiptItemDescriptionFor(product)
            val receiptItem = Receipt.Item(it.qty, receiptItemDescription, taxedPrice.value)
            items.add(receiptItem)

            totalTaxAmount += taxAmount
            totalTaxedPrice += taxedPrice
        }

        return Receipt(items, totalTaxAmount.value, totalTaxedPrice.value)
    }

    private fun receiptItemDescriptionFor(it: Product): String {
        if (it.isImported)
            return "imported ${it.description}"
        return it.description
    }

}
