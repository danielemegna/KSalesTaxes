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
        val itemsWithTax = shoppingBasket.items.map { it.withTax() }

        val totalTaxAmount = itemsWithTax
            .map { it.taxAmount() }
            .reduce(TaxAmount::plus)

        val totalTaxedPrice = itemsWithTax
            .map { it.taxedPrice() }
            .reduce(TaxedPrice::plus)

        val items = itemsWithTax.map {
            Receipt.Item(it.qty, it.description, it.taxedPrice().value)
        }

        return Receipt(items, totalTaxAmount.value, totalTaxedPrice.value)
    }

    private fun ShoppingBasket.Item.withTax(): ShoppingBasketItemWithTax {
        val product = productFactory.from(this.description)

        val unitTaxAmount = taxAmountCalculator.getFor(
            taxRate = taxRules.getTaxRateFor(product),
            netPrice = NetPrice.of(this.unitNetPrice)
        )
        return ShoppingBasketItemWithTax(
            qty = this.qty,
            description = receiptItemDescriptionFor(product),
            unitNetPrice = NetPrice.of(this.unitNetPrice),
            unitTaxAmount = unitTaxAmount
        )
    }

    private fun receiptItemDescriptionFor(it: Product): String {
        if (it.isImported)
            return "imported ${it.description}"
        return it.description
    }

}
