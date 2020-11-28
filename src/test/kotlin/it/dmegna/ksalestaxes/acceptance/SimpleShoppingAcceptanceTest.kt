package it.dmegna.ksalestaxes.acceptance

import it.dmegna.ksalestaxes.Receipt
import it.dmegna.ksalestaxes.ShoppingBasket
import org.junit.Assert.assertEquals
import org.junit.Test

class SimpleShoppingAcceptanceTest {

    private val cashRegister = AcceptanceUtil.buildHappyLandCashRegister()

    @Test
    fun `working with single untaxed products`() {
        val shoppingBasket = ShoppingBasket().apply {
            add(qty = 1, description = "book", unitNetPrice = 12.49)
            add(qty = 1, description = "chocolate bar", unitNetPrice = 0.85)
        }

        val actualReceipt = cashRegister.receiptFor(shoppingBasket)

        assertEquals(Receipt.Item(1, "book", 12.49), actualReceipt.items[0])
        assertEquals(Receipt.Item(1, "chocolate bar", 0.85), actualReceipt.items[1])
        assertEquals(0.0, actualReceipt.salesTaxes, 0.0)
        assertEquals(12.49 + 0.85, actualReceipt.total, 0.0)
    }

    @Test
    fun `working with multiple untaxed products`() {
        val shoppingBasket = ShoppingBasket().apply {
            add(qty = 1, description = "book", unitNetPrice = 12.49)
            add(qty = 3, description = "chocolate bar", unitNetPrice = 0.85)
            add(qty = 2, description = "packet of headache pills", unitNetPrice = 9.75)
        }

        val actualReceipt = cashRegister.receiptFor(shoppingBasket)

        assertEquals(Receipt.Item(1, "book", 12.49), actualReceipt.items[0])
        assertEquals(Receipt.Item(3, "chocolate bar", 0.85 * 3), actualReceipt.items[1])
        assertEquals(Receipt.Item(2, "packet of headache pills", 9.75 * 2), actualReceipt.items[2])
        assertEquals(0.0, actualReceipt.salesTaxes, 0.0)
        assertEquals(12.49 + 2.55 + 19.5, actualReceipt.total, 0.0)
    }
}