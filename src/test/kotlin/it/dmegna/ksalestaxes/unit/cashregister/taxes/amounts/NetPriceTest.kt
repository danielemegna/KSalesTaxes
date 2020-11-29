package it.dmegna.ksalestaxes.unit.cashregister.taxes.amounts

import it.dmegna.ksalestaxes.cashregister.taxes.amounts.NetPrice
import it.dmegna.ksalestaxes.cashregister.taxes.amounts.TaxAmount
import it.dmegna.ksalestaxes.cashregister.taxes.amounts.TaxedPrice
import org.junit.Assert.assertEquals
import org.junit.Test

class NetPriceTest {

    @Test
    fun `adding a tax amount to a net price produce a taxed price`() {
        val actual: TaxedPrice = NetPrice.of(10.30) + TaxAmount.of(2.30)
        assertEquals(TaxedPrice.of(12.60), actual)
    }

}