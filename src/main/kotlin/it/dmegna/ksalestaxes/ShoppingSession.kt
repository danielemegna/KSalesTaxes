package it.dmegna.ksalestaxes

import it.dmegna.ksalestaxes.cashregister.Receipt
import it.dmegna.ksalestaxes.cashregister.ShoppingBasket
import it.dmegna.ksalestaxes.cashregister.ShoppingBasketProcessor
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
