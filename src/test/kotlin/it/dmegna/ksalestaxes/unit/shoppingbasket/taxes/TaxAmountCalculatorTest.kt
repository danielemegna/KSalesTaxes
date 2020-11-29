package it.dmegna.ksalestaxes.unit.shoppingbasket.taxes

import it.dmegna.ksalestaxes.shoppingbasket.taxes.TaxAmountCalculator
import it.dmegna.ksalestaxes.shoppingbasket.taxes.TaxesRoundRule
import it.dmegna.ksalestaxes.shoppingbasket.taxes.amounts.NetPrice
import it.dmegna.ksalestaxes.shoppingbasket.taxes.amounts.TaxAmount
import it.dmegna.ksalestaxes.shoppingbasket.taxes.amounts.TaxRate
import org.junit.Assert.assertEquals
import org.junit.Test

class TaxAmountCalculatorTest {

    private val calculator = TaxAmountCalculator(ProxyAsIsTaxesRoundRule())

    @Test
    fun `zero tax rate, zero tax amount`() {
        assertEquals(TaxAmount.of(0.0), with(taxRate = 0.0, netPrice = 10.99))
    }

    @Test
    fun `one tax rate, tax amount as netPrice`() {
        assertEquals(TaxAmount.of(15.99), with(taxRate = 1.0, netPrice = 15.99))
    }

    @Test
    fun `apply tax rate to net price`() {
        assertEquals(TaxAmount.of(1.279), with(taxRate = 0.10, netPrice = 12.79))
        assertEquals(TaxAmount.of(5.495), with(taxRate = 0.50, netPrice = 10.99))
    }

    @Test
    fun `delegates to RoundRule the result amount rounding`() {
        val calulatorWithStubRoundRule = TaxAmountCalculator(StubTaxesRoundRule(11.11))
        val actual = calulatorWithStubRoundRule.getFor(TaxRate.of(9999.99), NetPrice.of(9999.99))
        assertEquals(TaxAmount.of(11.11), actual)
    }

    private fun with(taxRate: Double, netPrice: Double) =
        calculator.getFor(TaxRate.of(taxRate), NetPrice.of(netPrice))

    private class ProxyAsIsTaxesRoundRule : TaxesRoundRule {
        override fun round(taxAmount: TaxAmount) = taxAmount
    }

    private class StubTaxesRoundRule(val stubValue: Double) : TaxesRoundRule {
        override fun round(taxAmount: TaxAmount) = TaxAmount.of(stubValue)
    }

}

