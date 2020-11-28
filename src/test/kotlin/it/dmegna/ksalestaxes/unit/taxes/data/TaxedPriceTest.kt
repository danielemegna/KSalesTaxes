package it.dmegna.ksalestaxes.unit.taxes.data

import it.dmegna.ksalestaxes.taxes.data.TaxedPrice
import org.junit.Assert.assertEquals
import org.junit.Test

class TaxedPriceTest {

    @Test
    fun `normalized price to 2 decimals`() {
        assertEquals(12.6, TaxedPrice.normalized(12.600000000000001).value, 0.0)
        assertEquals(12.7, TaxedPrice.normalized(12.699999999999999).value, 0.0)
        assertEquals(12.67, TaxedPrice.normalized(12.671111111111111).value, 0.0)
        assertEquals(13.0, TaxedPrice.normalized(12.999999999999999).value, 0.0)
    }
}