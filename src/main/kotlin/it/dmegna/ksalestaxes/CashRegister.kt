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

        shoppingBasket.items.forEach { shoppingBasketItem ->
            val product = productFactory.from(shoppingBasketItem.description)
            val taxRule = taxRules.getTaxRateFor(product)
            val unitNetPrice = NetPrice.of(shoppingBasketItem.unitNetPrice)
            val unitItemTaxes = taxAmountCalculator.getFor(taxRule, unitNetPrice)
            val taxedUnitPrice = unitNetPrice + unitItemTaxes
            val taxedTotalPrice = taxedUnitPrice * shoppingBasketItem.qty
            items.add(Receipt.Item(shoppingBasketItem.qty, shoppingBasketItem.description, taxedTotalPrice.value))
            salesTaxes += (unitItemTaxes * shoppingBasketItem.qty).value
        }

        return Receipt(items, salesTaxes)
    }

}
