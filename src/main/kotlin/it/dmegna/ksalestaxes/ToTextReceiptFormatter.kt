package it.dmegna.ksalestaxes

import it.dmegna.ksalestaxes.shoppingbasket.outbound.Receipt
import it.dmegna.ksalestaxes.shoppingbasket.outbound.ReceiptFormatter
import java.lang.System.lineSeparator

class ToTextReceiptFormatter : ReceiptFormatter<String> {
    override fun format(receipt: Receipt): String {
        return receipt.items
            .map { this.format(it) }
            .plus("Sales Taxes: %.2f".format(receipt.salesTaxes))
            .plus("Total: %.2f".format(receipt.total))
            .joinToString(lineSeparator())
    }

    private fun format(item: Receipt.Item): String {
        return "%d %s: %.2f".format(
            item.qty, item.description, item.taxedTotalPrice
        )
    }
}