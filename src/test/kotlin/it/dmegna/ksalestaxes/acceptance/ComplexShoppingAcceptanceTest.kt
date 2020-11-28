package it.dmegna.ksalestaxes.acceptance

import it.dmegna.ksalestaxes.Receipt
import it.dmegna.ksalestaxes.ShoppingBasket
import org.junit.Assert.assertEquals
import org.junit.Test

class ComplexShoppingAcceptanceTest {

    private val cashRegister = AcceptanceUtil.buildHappyLandCashRegister()

    @Test
    fun `working with basic taxed products`() {
        val shoppingBasket = ShoppingBasket().apply {
            add(qty = 1, description = "music CD", unitNetPrice = 14.99)
            add(qty = 1, description = "bottle of perfume", unitNetPrice = 18.99)
        }

        val actualReceipt = cashRegister.receiptFor(shoppingBasket)

        assertEquals(Receipt.Item(1, "music CD", 16.49), actualReceipt.items[0])
        assertEquals(Receipt.Item(1, "bottle of perfume", 20.89), actualReceipt.items[1])
        assertEquals(1.5 + 1.9, actualReceipt.salesTaxes, 0.0)
        assertEquals(16.49 + 20.89, actualReceipt.getTotal(), 0.0)
    }

    @Test
    fun `mix taxed and untaxed products`() {
        val shoppingBasket = ShoppingBasket().apply {
            add(qty = 1, description = "book", unitNetPrice = 12.49)
            add(qty = 1, description = "music CD", unitNetPrice = 14.99)
            add(qty = 1, description = "chocolate bar", unitNetPrice = 0.85)
        }

        val actualReceipt = cashRegister.receiptFor(shoppingBasket)

        assertEquals(Receipt.Item(1, "book", 12.49), actualReceipt.items[0])
        assertEquals(Receipt.Item(1, "music CD", 16.49), actualReceipt.items[1])
        assertEquals(Receipt.Item(1, "chocolate bar", 0.85), actualReceipt.items[2])
        assertEquals(1.50, actualReceipt.salesTaxes, 0.0)
        assertEquals(29.83, actualReceipt.getTotal(), 0.0)
    }

    @Test
    fun `working with imported products`() {
        val shoppingBasket = ShoppingBasket().apply {
            add(qty = 1, description = "imported box of chocolates", unitNetPrice = 10.00)
            add(qty = 1, description = "imported bottle of perfume", unitNetPrice = 47.50)
        }

        val actualReceipt = cashRegister.receiptFor(shoppingBasket)

        assertEquals(Receipt.Item(1, "imported box of chocolates", 10.50), actualReceipt.items[0])
        assertEquals(Receipt.Item(1, "imported bottle of perfume", 54.65), actualReceipt.items[1])
        assertEquals(7.65, actualReceipt.salesTaxes, 0.0)
        assertEquals(65.15, actualReceipt.getTotal(), 0.0)
    }

    // working with imported taxed products
    // test mixed untaxed, basic taxed and imported
}