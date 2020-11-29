package it.dmegna.ksalestaxes.unit.shoppingbasket

import it.dmegna.ksalestaxes.shoppingbasket.ShoppingBasketItemWithTax
import it.dmegna.ksalestaxes.shoppingbasket.taxes.amounts.NetPrice
import it.dmegna.ksalestaxes.shoppingbasket.taxes.amounts.TaxAmount
import it.dmegna.ksalestaxes.shoppingbasket.taxes.amounts.TaxedPrice
import org.junit.Assert.assertEquals
import org.junit.Test

class ShoppingBasketItemWithTaxTest {

    @Test
    fun `implement taxAmount as 'unitTaxAmount times quantity'`() {
        val taxedItem = aShoppingBasketItemWithTax().copy(unitTaxAmount = TaxAmount.of(1.55))
        assertEquals(TaxAmount.of(1.55), taxedItem.copy(qty = 1).taxAmount())
        assertEquals(TaxAmount.of(1.55 * 3), taxedItem.copy(qty = 3).taxAmount())
    }

    @Test
    fun `implement taxedPrice as '(unitNetPrice plus unitTaxAmount) times quantity'`() {
        val taxedItem = aShoppingBasketItemWithTax().copy(
            unitNetPrice = NetPrice.of(10.00),
            unitTaxAmount = TaxAmount.of(1.55)
        )
        assertEquals(TaxedPrice.of(10.00 + 1.55), taxedItem.copy(qty = 1).taxedPrice())
        assertEquals(TaxedPrice.of((10.00 + 1.55) * 3), taxedItem.copy(qty = 3).taxedPrice())
    }

    private fun aShoppingBasketItemWithTax(): ShoppingBasketItemWithTax {
        return ShoppingBasketItemWithTax(
            qty = 2,
            description = "product description",
            unitNetPrice = NetPrice.of(14.99),
            unitTaxAmount = TaxAmount.of(0.85)
        )
    }
}