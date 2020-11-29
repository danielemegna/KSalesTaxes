package it.dmegna.ksalestaxes.acceptance

import it.dmegna.ksalestaxes.FromTextShoppingBasketAdapter
import it.dmegna.ksalestaxes.ShoppingSession
import it.dmegna.ksalestaxes.ShoppingSessionInputProvider
import it.dmegna.ksalestaxes.ToTextReceiptFormatter
import it.dmegna.ksalestaxes.cashregister.CashRegister
import it.dmegna.ksalestaxes.cashregister.products.HappyLandProductFactory
import it.dmegna.ksalestaxes.cashregister.taxes.HappyLandTaxRules
import it.dmegna.ksalestaxes.cashregister.taxes.HappyLandTaxesRoundRule
import it.dmegna.ksalestaxes.cashregister.taxes.TaxAmountCalculator
import org.junit.Assert.assertEquals
import org.junit.Test

class E2ETest {

    @Test
    fun `first scenario`() {
        val input = """
            1 book at 12.49
            1 music CD at 14.99
            1 chocolate bar at 0.85
        """.trimIndent()

        val actual = runWith(input)

        val expectedOutput = """
           1 book: 12.49
           1 music CD: 16.49
           1 chocolate bar: 0.85
           Sales Taxes: 1.50
           Total: 29.83
        """.trimIndent()
        assertEquals(expectedOutput, actual)
    }

    @Test
    fun `second scenario`() {
        val input = """
            1 imported box of chocolates at 10.00
            1 imported bottle of perfume at 47.50
        """.trimIndent()

        val actual = runWith(input)

        val expectedOutput = """
           1 imported box of chocolates: 10.50
           1 imported bottle of perfume: 54.65
           Sales Taxes: 7.65
           Total: 65.15
        """.trimIndent()
        assertEquals(expectedOutput, actual)
    }

    @Test
    fun `third scenario`() {
        val input = """
            1 imported bottle of perfume at 27.99
            1 bottle of perfume at 18.99
            1 packet of headache pills at 9.75
            1 box of imported chocolates at 11.25
        """.trimIndent()

        val actual = runWith(input)

        val expectedOutput = """
            1 imported bottle of perfume: 32.19
            1 bottle of perfume: 20.89
            1 packet of headache pills: 9.75
            1 imported box of chocolates: 11.85
            Sales Taxes: 6.70
            Total: 74.68
        """.trimIndent()
        assertEquals(expectedOutput, actual)
    }

    @Test
    fun `extra scenario`() {
        val input = """
            1 imported bottle of perfume at 27.99
            1 bottle of perfume at 18.99
            1 packet of headache pills at 9.75
            3 box of imported chocolates at 11.25
        """.trimIndent()

        val actual = runWith(input)

        val expectedOutput = """
            1 imported bottle of perfume: 32.19
            1 bottle of perfume: 20.89
            1 packet of headache pills: 9.75
            3 imported box of chocolates: 35.55
            Sales Taxes: 7.90
            Total: 98.38
        """.trimIndent()
        assertEquals(expectedOutput, actual)
    }

    private fun runWith(input: String): String {
        return ShoppingSession(
            inputProvider = StubShoppingSessionInputProvider(input),
            shoppingBasketAdapter = FromTextShoppingBasketAdapter(),
            shoppingBasketProcessor = CashRegister(
                productFactory = HappyLandProductFactory(),
                taxRules = HappyLandTaxRules(),
                taxAmountCalculator = TaxAmountCalculator(
                    taxesRoundRule = HappyLandTaxesRoundRule()
                )
            ),
            receiptFormatter = ToTextReceiptFormatter()
        ).run()
    }

    class StubShoppingSessionInputProvider(
        private val stubbedInput: String
    ) : ShoppingSessionInputProvider<String> {
        override fun readAll() = stubbedInput.split("\n")
    }
}