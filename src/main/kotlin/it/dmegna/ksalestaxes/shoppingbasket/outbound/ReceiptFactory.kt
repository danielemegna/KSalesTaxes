package it.dmegna.ksalestaxes.shoppingbasket.outbound

import it.dmegna.ksalestaxes.shoppingbasket.ShoppingBasketItemWithTax
import it.dmegna.ksalestaxes.shoppingbasket.taxes.amounts.TaxAmount
import it.dmegna.ksalestaxes.shoppingbasket.taxes.amounts.TaxedPrice

class ReceiptFactory {

    fun from(items: Iterable<ShoppingBasketItemWithTax>): Receipt {
        val totalTaxAmount = items
            .map { it.taxAmount() }
            .reduce(TaxAmount::plus)

        val totalTaxedPrice = items
            .map { it.taxedPrice() }
            .reduce(TaxedPrice::plus)

        val receiptItems = items.map {
            Receipt.Item(it.qty, it.description, it.taxedPrice().value)
        }

        return Receipt(receiptItems, totalTaxAmount.value, totalTaxedPrice.value)
    }
}