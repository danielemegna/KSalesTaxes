package it.dmegna.ksalestaxes

import it.dmegna.ksalestaxes.cashregister.CashRegister
import it.dmegna.ksalestaxes.ShoppingSessionInputProvider as InputProvider

class ShoppingSession<InputType, OutputType>(
    private val inputProvider: InputProvider<InputType>,
    private val shoppingBasketAdapter: ShoppingBasketAdapter<InputType>,
    private val cashRegister: CashRegister,
    private val receiptFormatter: ReceiptFormatter<OutputType>

) {
    fun run(): OutputType {
        val basket = shoppingBasketAdapter.from(inputProvider.readAll())
        val receipt = cashRegister.receiptFor(basket)
        return receiptFormatter.format(receipt)
    }

}
