package it.dmegna.ksalestaxes.unit.shoppingbasket.outbound

import it.dmegna.ksalestaxes.shoppingbasket.ShoppingBasketItemWithTax
import it.dmegna.ksalestaxes.shoppingbasket.outbound.Receipt
import it.dmegna.ksalestaxes.shoppingbasket.outbound.ReceiptFactory
import it.dmegna.ksalestaxes.shoppingbasket.taxes.amounts.NetPrice
import it.dmegna.ksalestaxes.shoppingbasket.taxes.amounts.TaxAmount
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.random.Random

/*
    invariant testing properties of some
    built receipt using list of random items
*/
class ReceiptFactoryInvariantTest {

    private val receiptFactory = ReceiptFactory()

    @Test
    fun `sum taxed prices from items`() {
        val first = aRandomItem()
        val second = aRandomItem()
        val third = aRandomItem()

        val actual = receiptFactory.from(listOf(first, second, third))

        assertEquals(
            (first.taxedPrice() + second.taxedPrice() + third.taxedPrice()).value,
            actual.total,
            0.0
        )
    }

    @Test
    fun `sum tax amounts from items`() {
        val first = aRandomItem()
        val second = aRandomItem()
        val third = aRandomItem()

        val actual = receiptFactory.from(listOf(first, second, third))

        assertEquals(
            (first.taxAmount() + second.taxAmount() + third.taxAmount()).value,
            actual.salesTaxes,
            0.0
        )
    }

    @Test
    fun `properly map items`() {
        val first = aRandomItem()
        val second = aRandomItem()
        val third = aRandomItem()

        val actual = receiptFactory.from(listOf(first, second, third))

        assertEquals(
            listOf(
                Receipt.Item(first.qty, first.description, first.taxedPrice().value),
                Receipt.Item(second.qty, second.description, second.taxedPrice().value),
                Receipt.Item(third.qty, third.description, third.taxedPrice().value)
            ),
            actual.items
        )
    }

    private fun aRandomItem(): ShoppingBasketItemWithTax {
        val random = Random(System.nanoTime())
        return ShoppingBasketItemWithTax(
            qty = random.nextInt(1, 11),
            description = "random item description ${random.nextInt()}",
            unitNetPrice = NetPrice.of(random.nextDouble(0.01, 999.99)),
            unitTaxAmount = TaxAmount.of(random.nextDouble(0.01, 99.99))
        )
    }
}