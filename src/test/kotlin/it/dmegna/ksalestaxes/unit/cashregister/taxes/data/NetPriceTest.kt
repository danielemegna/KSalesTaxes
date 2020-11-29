package it.dmegna.ksalestaxes.unit.cashregister.taxes.data

import it.dmegna.ksalestaxes.cashregister.taxes.data.NetPrice
import it.dmegna.ksalestaxes.cashregister.taxes.data.TaxAmount
import it.dmegna.ksalestaxes.cashregister.taxes.data.TaxedPrice
import org.junit.Assert.assertEquals
import org.junit.Test

class NetPriceTest {
    @Test
    fun `adding a tax amount we get a taxed price`() {
        val actual: TaxedPrice = NetPrice.of(10.30) + TaxAmount.of(2.30)
        assertEquals(12.60, actual.value, 0.0)
    }
}