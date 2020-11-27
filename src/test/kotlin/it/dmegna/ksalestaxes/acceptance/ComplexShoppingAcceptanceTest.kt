package it.dmegna.ksalestaxes.acceptance

import it.dmegna.ksalestaxes.CashRegister
import it.dmegna.ksalestaxes.Receipt
import it.dmegna.ksalestaxes.ShoppingBasket
import org.junit.Assert.assertEquals
import org.junit.Ignore
import org.junit.Test

class ComplexShoppingAcceptanceTest {

    private val cashRegister = CashRegister()

    @Ignore("WIP")
    @Test
    fun `working with basic taxed products`() {
        val shoppingBasket = ShoppingBasket().apply {
            add(qty = 1, description = "music CD", unitPrice = 14.99)
            add(qty = 1, description = "bottle of perfume", unitPrice = 18.99)
        }

        val actualReceipt = cashRegister.receiptFor(shoppingBasket)

        assertEquals(Receipt.Item(1, "music CD", 16.49), actualReceipt.items[0])
        assertEquals(Receipt.Item(1, "bottle of perfume", 20.89), actualReceipt.items[1])
        assertEquals(1.5 + 1.9, actualReceipt.salesTaxes, 0.0)
        assertEquals(16.49 + 20.89, actualReceipt.getTotal(), 0.0)
    }

    // working with imported products
    // working with imported taxed products
    // test mixed untaxed, basic taxed and imported
}