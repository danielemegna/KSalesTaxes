package it.dmegna.ksalestaxes.acceptance

import it.dmegna.ksalestaxes.CashRegister
import it.dmegna.ksalestaxes.Receipt
import it.dmegna.ksalestaxes.ShoppingBasket
import org.junit.Assert.assertEquals
import org.junit.Test

class SimpleShoppingAcceptanceTest {

    private val cashRegister = CashRegister()

    @Test
    fun `working with single untaxed products`() {
        val shoppingBasket = ShoppingBasket()
        shoppingBasket.add(qty = 1, description = "book", unitPrice = 12.49)
        shoppingBasket.add(qty = 1, description = "chocolate bar", unitPrice = 0.85)

        val actualReceipt = cashRegister.receiptFor(shoppingBasket)

        val expectedReceipt = Receipt(
            items = listOf(
                Receipt.Item(1, "book", 12.49),
                Receipt.Item(1, "chocolate bar", 0.85)
            ),
            salesTaxes = 0.0
        )
        assertEquals(expectedReceipt, actualReceipt)
    }

    @Test
    fun `working with multiple untaxed products`() {
        val shoppingBasket = ShoppingBasket()
        shoppingBasket.add(qty = 1, description = "book", unitPrice = 12.49)
        shoppingBasket.add(qty = 3, description = "chocolate bar", unitPrice = 0.85)
        shoppingBasket.add(qty = 2, description = "packet of headache pills", unitPrice = 9.75)

        val actualReceipt = cashRegister.receiptFor(shoppingBasket)

        val expectedReceipt = Receipt(
            items = listOf(
                Receipt.Item(1, "book", 12.49),
                Receipt.Item(3, "chocolate bar", 0.85 * 3),
                Receipt.Item(2, "packet of headache pills", 9.75 * 2)
            ),
            salesTaxes = 0.0
        )
        assertEquals(expectedReceipt, actualReceipt)
    }
}