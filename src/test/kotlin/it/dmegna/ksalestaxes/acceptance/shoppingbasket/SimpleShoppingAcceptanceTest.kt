package it.dmegna.ksalestaxes.acceptance.shoppingbasket

import it.dmegna.ksalestaxes.shoppingbasket.CashRegister
import it.dmegna.ksalestaxes.shoppingbasket.inbound.ShoppingBasket
import it.dmegna.ksalestaxes.shoppingbasket.inbound.ShoppingBasket.Item
import it.dmegna.ksalestaxes.shoppingbasket.outbound.Receipt
import it.dmegna.ksalestaxes.shoppingbasket.outbound.ReceiptFactory
import it.dmegna.ksalestaxes.shoppingbasket.products.HappyLandProductFactory
import it.dmegna.ksalestaxes.shoppingbasket.taxes.HappyLandTaxRules
import it.dmegna.ksalestaxes.shoppingbasket.taxes.HappyLandTaxesRoundRule
import it.dmegna.ksalestaxes.shoppingbasket.taxes.TaxAmountCalculator
import org.junit.Assert.assertEquals
import org.junit.Test

class SimpleShoppingAcceptanceTest {

    private val cashRegister = buildHappyLandCashRegister()

    @Test
    fun `working with single untaxed products`() {
        val shoppingBasket = ShoppingBasket(
            listOf(
                Item(qty = 1, description = "book", unitNetPrice = 12.49),
                Item(qty = 1, description = "chocolate bar", unitNetPrice = 0.85)
            )
        )

        val actualReceipt = cashRegister.process(shoppingBasket)

        assertEquals(2, actualReceipt.items.size)
        assertEquals(Receipt.Item(1, "book", 12.49), actualReceipt.items[0])
        assertEquals(Receipt.Item(1, "chocolate bar", 0.85), actualReceipt.items[1])
        assertEquals(0.0, actualReceipt.salesTaxes, 0.0)
        assertEquals(12.49 + 0.85, actualReceipt.total, 0.0)
    }

    @Test
    fun `working with multiple untaxed products`() {
        val shoppingBasket = ShoppingBasket(
            listOf(
                Item(qty = 1, description = "book", unitNetPrice = 12.49),
                Item(qty = 3, description = "chocolate bar", unitNetPrice = 0.85),
                Item(qty = 2, description = "packet of headache pills", unitNetPrice = 9.75)
            )
        )

        val actualReceipt = cashRegister.process(shoppingBasket)

        assertEquals(3, actualReceipt.items.size)
        assertEquals(Receipt.Item(1, "book", 12.49), actualReceipt.items[0])
        assertEquals(Receipt.Item(3, "chocolate bar", 0.85 * 3), actualReceipt.items[1])
        assertEquals(Receipt.Item(2, "packet of headache pills", 9.75 * 2), actualReceipt.items[2])
        assertEquals(0.0, actualReceipt.salesTaxes, 0.0)
        assertEquals(12.49 + 2.55 + 19.5, actualReceipt.total, 0.0)
    }
   
    private fun buildHappyLandCashRegister(): CashRegister {
        return CashRegister(
            productFactory = HappyLandProductFactory(),
            taxRules = HappyLandTaxRules(),
            taxAmountCalculator = TaxAmountCalculator(
                taxesRoundRule = HappyLandTaxesRoundRule()
            ),
            receiptFactory = ReceiptFactory()
        )
    }
}