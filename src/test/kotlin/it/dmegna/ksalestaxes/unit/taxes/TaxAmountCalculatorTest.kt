package it.dmegna.ksalestaxes.unit.taxes

import it.dmegna.ksalestaxes.taxes.TaxAmountCalculator
import it.dmegna.ksalestaxes.taxes.TaxesRoundRule
import it.dmegna.ksalestaxes.taxes.data.NetPrice
import it.dmegna.ksalestaxes.taxes.data.TaxAmount
import it.dmegna.ksalestaxes.taxes.data.TaxRate
import org.junit.Assert.assertEquals
import org.junit.Test

class TaxAmountCalculatorTest {

    private val calulator = TaxAmountCalculator(ProxyAsIsTaxesRoundRule())

    @Test
    fun `zero tax rate, zero tax amount`() {
        assertEquals(TaxAmount(0.0), with(taxRate = 0.0, netPrice = 10.99))
    }

    @Test
    fun `one tax rate, tax amount as netPrice`() {
        assertEquals(TaxAmount(15.99), with(taxRate = 1.0, netPrice = 15.99))
    }

    @Test
    fun `apply tax rate to net price`() {
        assertEquals(TaxAmount(1.279), with(taxRate = 0.10, netPrice = 12.79))
        assertEquals(TaxAmount(5.495), with(taxRate = 0.50, netPrice = 10.99))
    }

    @Test
    fun `delegates to RoundRule the result amount rounding`() {
        val calulatorWithStubRoundRule = TaxAmountCalculator(StubTaxesRoundRule(11.11))
        val actual = calulatorWithStubRoundRule.getFor(TaxRate(9999.99), NetPrice(9999.99))
        assertEquals(TaxAmount(11.11), actual)
    }

    private fun with(taxRate: Double, netPrice: Double) =
        calulator.getFor(TaxRate(taxRate), NetPrice(netPrice))

    private class ProxyAsIsTaxesRoundRule : TaxesRoundRule {
        override fun round(taxAmount: TaxAmount) = taxAmount
    }

    private class StubTaxesRoundRule(val stubValue: Double) : TaxesRoundRule {
        override fun round(taxAmount: TaxAmount) = TaxAmount(stubValue)
    }
}

