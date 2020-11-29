package it.dmegna.ksalestaxes.unit.shoppingbasket.taxes.amounts

import it.dmegna.ksalestaxes.shoppingbasket.taxes.amounts.NetPrice
import it.dmegna.ksalestaxes.shoppingbasket.taxes.amounts.TaxAmount
import it.dmegna.ksalestaxes.shoppingbasket.taxes.amounts.TaxRate
import it.dmegna.ksalestaxes.shoppingbasket.taxes.amounts.TaxedPrice
import org.junit.Assert.assertEquals
import org.junit.Test

class AmountsNormalizationTest {

    @Test
    fun `wrapped amounts, price and rates are normalized to 4 decimals`() {
        assertEquals(4.6, NetPrice.of(4.600000000000001).value, 0.0)
        assertEquals(12.6, NetPrice.of(12.600000000000001).value, 0.0)
        assertEquals(7.7, TaxAmount.of(7.699999999999999).value, 0.0)
        assertEquals(12.7, TaxAmount.of(12.699999999999999).value, 0.0)
        assertEquals(1.6711, TaxedPrice.of(1.671111111111111).value, 0.0)
        assertEquals(12.6711, TaxedPrice.of(12.671111111111111).value, 0.0)
        assertEquals(10.0, TaxRate.of(9.999999999999999).value, 0.0)
        assertEquals(13.0, TaxRate.of(12.999999999999999).value, 0.0)
    }

}