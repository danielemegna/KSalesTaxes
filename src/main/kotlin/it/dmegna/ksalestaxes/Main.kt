package it.dmegna.ksalestaxes

import it.dmegna.ksalestaxes.shoppingbasket.CashRegister
import it.dmegna.ksalestaxes.shoppingbasket.outbound.ReceiptFactory
import it.dmegna.ksalestaxes.shoppingbasket.products.HappyLandProductFactory
import it.dmegna.ksalestaxes.shoppingbasket.taxes.HappyLandTaxRules
import it.dmegna.ksalestaxes.shoppingbasket.taxes.HappyLandTaxesRoundRule
import it.dmegna.ksalestaxes.shoppingbasket.taxes.TaxAmountCalculator

fun main() {
    println("Sales Taxes")
    println("==========================================")
    val shoppingSession = buildShoppingSession()
    val formattedReceipt = shoppingSession.run()
    println(formattedReceipt)
}

private fun buildShoppingSession() = ShoppingSession(
    inputProvider = ConsoleShoppingSessionInputProvider(),
    shoppingBasketAdapter = FromTextShoppingBasketAdapter(),
    shoppingBasketProcessor = CashRegister(
        productFactory = HappyLandProductFactory(),
        taxRules = HappyLandTaxRules(),
        taxAmountCalculator = TaxAmountCalculator(
            taxesRoundRule = HappyLandTaxesRoundRule()
        ),
        receiptFactory = ReceiptFactory()
    ),
    receiptFormatter = ToTextReceiptFormatter()
)

class ConsoleShoppingSessionInputProvider : ShoppingSessionInputProvider<String> {
    override fun readAll(): Iterable<String> {
        println("Please provide shopping item descriptions:")
        val result = mutableListOf<String>()
        while (true) {
            val line = readLine()!!
            if (line.isBlank()) break
            result.add(line)
        }
        return result
    }

}

