package it.dmegna.ksalestaxes.unit

import it.dmegna.ksalestaxes.FromTextShoppingBasketAdapter
import it.dmegna.ksalestaxes.shoppingbasket.inbound.ShoppingBasket
import it.dmegna.ksalestaxes.shoppingbasket.inbound.ShoppingBasket.Item
import it.dmegna.ksalestaxes.shoppingbasket.inbound.ShoppingBasketAdapter
import org.junit.Assert.assertEquals
import org.junit.Test

class FromTextShoppingBasketAdapterTest {

    private val adapter = FromTextShoppingBasketAdapter()

    @Test
    fun `adapt list of string to a shopping basket`() {
        val actual = adapter.from(
            listOf(
                "2 book at 12.49",
                "1 music CD at 14.99",
                "3 chocolate bar at 0.85",
                "1 imported box of chocolates at 10.00",
                "11 bottle of perfume at 20.89"
            )
        )

        val expected = ShoppingBasket(
            listOf(
                Item(qty = 2, description = "book", unitNetPrice = 12.49),
                Item(qty = 1, description = "music CD", unitNetPrice = 14.99),
                Item(qty = 3, description = "chocolate bar", unitNetPrice = 0.85),
                Item(qty = 1, description = "imported box of chocolates", unitNetPrice = 10.00),
                Item(qty = 11, description = "bottle of perfume", unitNetPrice = 20.89)
            )
        )
        assertEquals(expected, actual)
    }

    @Test(expected = ShoppingBasketAdapter.InvalidInputException::class)
    fun `invalid input`() {
        adapter.from(listOf("invalid"))
    }

    @Test(expected = ShoppingBasketAdapter.InvalidInputException::class)
    fun `valid input format with invalid quantity value`() {
        adapter.from(listOf("one music CD at 14.99"))
    }

    @Test(expected = ShoppingBasketAdapter.InvalidInputException::class)
    fun `valid input format with invalid price value`() {
        adapter.from(listOf("1 music CD at twenty"))
    }
}