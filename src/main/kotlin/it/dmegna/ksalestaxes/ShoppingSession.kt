package it.dmegna.ksalestaxes

import it.dmegna.ksalestaxes.shoppingbasket.ShoppingBasketProcessor
import it.dmegna.ksalestaxes.shoppingbasket.inbound.ShoppingBasket
import it.dmegna.ksalestaxes.shoppingbasket.inbound.ShoppingBasketAdapter
import it.dmegna.ksalestaxes.shoppingbasket.outbound.Receipt
import it.dmegna.ksalestaxes.shoppingbasket.outbound.ReceiptFormatter
import it.dmegna.ksalestaxes.ShoppingSessionInputProvider as InputProvider

class ShoppingSession<InputType, OutputType>(
    private val inputProvider: InputProvider<InputType>,
    private val shoppingBasketAdapter: ShoppingBasketAdapter<InputType>,
    private val shoppingBasketProcessor: ShoppingBasketProcessor,
    private val receiptFormatter: ReceiptFormatter<OutputType>

) {
    fun run(): OutputType {
        val shoppingBasket: ShoppingBasket = shoppingBasketAdapter.from(inputProvider.readAll())
        val receipt: Receipt = shoppingBasketProcessor.process(shoppingBasket)
        return receiptFormatter.format(receipt)
    }

}
