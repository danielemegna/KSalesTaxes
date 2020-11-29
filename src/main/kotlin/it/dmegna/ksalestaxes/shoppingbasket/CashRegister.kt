package it.dmegna.ksalestaxes.shoppingbasket

import it.dmegna.ksalestaxes.shoppingbasket.inbound.ShoppingBasket
import it.dmegna.ksalestaxes.shoppingbasket.outbound.Receipt
import it.dmegna.ksalestaxes.shoppingbasket.outbound.ReceiptFactory
import it.dmegna.ksalestaxes.shoppingbasket.products.ProductFactory
import it.dmegna.ksalestaxes.shoppingbasket.taxes.TaxAmountCalculator
import it.dmegna.ksalestaxes.shoppingbasket.taxes.TaxRules
import it.dmegna.ksalestaxes.shoppingbasket.taxes.amounts.NetPrice

class CashRegister(
    private val productFactory: ProductFactory,
    private val taxRules: TaxRules,
    private val taxAmountCalculator: TaxAmountCalculator,
    private val receiptFactory: ReceiptFactory
) : ShoppingBasketProcessor {

    override fun process(shoppingBasket: ShoppingBasket): Receipt {
        val itemsWithTax = shoppingBasket.items.map { it.withTax() }
        return receiptFactory.from(itemsWithTax)
    }

    private fun ShoppingBasket.Item.withTax(): ShoppingBasketItemWithTax {
        val product = productFactory.from(this.description)

        val unitTaxAmount = taxAmountCalculator.getFor(
            taxRate = taxRules.getTaxRateFor(product),
            netPrice = NetPrice.of(this.unitNetPrice)
        )
        return ShoppingBasketItemWithTax(
            qty = this.qty,
            description = product.description,
            unitNetPrice = NetPrice.of(this.unitNetPrice),
            unitTaxAmount = unitTaxAmount
        )
    }

}
