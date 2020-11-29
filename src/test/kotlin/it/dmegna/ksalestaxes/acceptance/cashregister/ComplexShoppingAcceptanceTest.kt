package it.dmegna.ksalestaxes.acceptance.cashregister

import it.dmegna.ksalestaxes.cashregister.Receipt
import it.dmegna.ksalestaxes.cashregister.ShoppingBasket
import it.dmegna.ksalestaxes.cashregister.ShoppingBasket.Item
import org.junit.Assert.assertEquals
import org.junit.Test

class ComplexShoppingAcceptanceTest {

    private val cashRegister = AcceptanceUtil.buildHappyLandCashRegister()

    @Test
    fun `working with basic taxed products`() {
        val shoppingBasket = ShoppingBasket(
            listOf(
                Item(qty = 1, description = "music CD", unitNetPrice = 14.99),
                Item(qty = 1, description = "bottle of perfume", unitNetPrice = 18.99)
            )
        )

        val actualReceipt = cashRegister.receiptFor(shoppingBasket)

        assertEquals(Receipt.Item(1, "music CD", 16.49), actualReceipt.items[0])
        assertEquals(Receipt.Item(1, "bottle of perfume", 20.89), actualReceipt.items[1])
        assertEquals(3.4, actualReceipt.salesTaxes, 0.0)
        assertEquals(37.38, actualReceipt.total, 0.0)
    }

    @Test
    fun `mix taxed and untaxed products`() {
        val shoppingBasket = ShoppingBasket(
            listOf(
                Item(qty = 1, description = "book", unitNetPrice = 12.49),
                Item(qty = 1, description = "music CD", unitNetPrice = 14.99),
                Item(qty = 1, description = "chocolate bar", unitNetPrice = 0.85)
            )
        )

        val actualReceipt = cashRegister.receiptFor(shoppingBasket)

        assertEquals(Receipt.Item(1, "book", 12.49), actualReceipt.items[0])
        assertEquals(Receipt.Item(1, "music CD", 16.49), actualReceipt.items[1])
        assertEquals(Receipt.Item(1, "chocolate bar", 0.85), actualReceipt.items[2])
        assertEquals(1.50, actualReceipt.salesTaxes, 0.0)
        assertEquals(29.83, actualReceipt.total, 0.0)
    }

    @Test
    fun `working with imported products`() {
        val shoppingBasket = ShoppingBasket(
            listOf(
                Item(qty = 1, description = "imported box of chocolates", unitNetPrice = 10.00),
                Item(qty = 1, description = "imported bottle of perfume", unitNetPrice = 47.50)
            )
        )

        val actualReceipt = cashRegister.receiptFor(shoppingBasket)

        assertEquals(Receipt.Item(1, "imported box of chocolates", 10.50), actualReceipt.items[0])
        assertEquals(Receipt.Item(1, "imported bottle of perfume", 54.65), actualReceipt.items[1])
        assertEquals(7.65, actualReceipt.salesTaxes, 0.0)
        assertEquals(65.15, actualReceipt.total, 0.0)
    }

    @Test
    fun `mixed basket`() {
        val shoppingBasket = ShoppingBasket(
            listOf(
                Item(qty = 1, description = "imported bottle of perfume", unitNetPrice = 27.99),
                Item(qty = 1, description = "bottle of perfume", unitNetPrice = 18.99),
                Item(qty = 1, description = "packet of headache pills", unitNetPrice = 9.75),
                Item(qty = 3, description = "box of imported chocolates", unitNetPrice = 11.25)
            )
        )

        val actualReceipt = cashRegister.receiptFor(shoppingBasket)

        assertEquals(Receipt.Item(1, "imported bottle of perfume", 32.19), actualReceipt.items[0])
        assertEquals(Receipt.Item(1, "bottle of perfume", 20.89), actualReceipt.items[1])
        assertEquals(Receipt.Item(1, "packet of headache pills", 9.75), actualReceipt.items[2])
        assertEquals(Receipt.Item(3, "imported box of chocolates", 35.55), actualReceipt.items[3])
        assertEquals(7.90, actualReceipt.salesTaxes, 0.0)
        assertEquals(98.38, actualReceipt.total, 0.0)
    }

}