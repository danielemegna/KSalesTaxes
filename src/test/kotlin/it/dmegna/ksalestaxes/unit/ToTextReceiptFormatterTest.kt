package it.dmegna.ksalestaxes.unit

import it.dmegna.ksalestaxes.ToTextReceiptFormatter
import it.dmegna.ksalestaxes.shoppingbasket.outbound.Receipt
import it.dmegna.ksalestaxes.shoppingbasket.outbound.Receipt.Item
import org.junit.Assert.assertEquals
import org.junit.Test

class ToTextReceiptFormatterTest {
    private val formatter = ToTextReceiptFormatter()

    @Test
    fun `format properly a receipt`() {
        val receipt = Receipt(
            items = listOf(
                Item(qty = 2, description = "book", taxedTotalPrice = 24.49),
                Item(qty = 1, description = "music CD", taxedTotalPrice = 16.49),
                Item(qty = 3, description = "chocolate bar", taxedTotalPrice = 1.85)
            ),
            salesTaxes = 3.50,
            total = 42.83
        )

        val actual = formatter.format(receipt)

        val expected = """
           2 book: 24.49
           1 music CD: 16.49
           3 chocolate bar: 1.85
           Sales Taxes: 3.50
           Total: 42.83
        """.trimIndent()
        assertEquals(expected, actual)
    }
}