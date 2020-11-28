package it.dmegna.ksalestaxes.unit.taxes

import it.dmegna.ksalestaxes.taxes.HappyLandTaxesRoundRule
import it.dmegna.ksalestaxes.taxes.TaxesRoundRule
import it.dmegna.ksalestaxes.taxes.data.TaxAmount
import org.junit.Assert.assertEquals
import org.junit.Test

class HappyLandTaxesRoundRuleTest {

    private val taxesRoundRule: TaxesRoundRule = HappyLandTaxesRoundRule()

    @Test
    fun `keep values already rounded to 0,05`() {
        assertEquals(TaxAmount(1.0), taxesRoundRule.round(TaxAmount(1.0)))
        assertEquals(TaxAmount(0.5), taxesRoundRule.round(TaxAmount(0.5)))
        assertEquals(TaxAmount(0.05), taxesRoundRule.round(TaxAmount(0.05)))
        assertEquals(TaxAmount(7.05), taxesRoundRule.round(TaxAmount(7.05)))
        assertEquals(TaxAmount(19.85), taxesRoundRule.round(TaxAmount(19.85)))
    }

    @Test
    fun `round up to the nearest 0,05`() {
        assertEquals(TaxAmount(9.10), taxesRoundRule.round(TaxAmount(9.11)))
        assertEquals(TaxAmount(9.15), taxesRoundRule.round(TaxAmount(9.13)))
        assertEquals(TaxAmount(9.15), taxesRoundRule.round(TaxAmount(9.16)))
        assertEquals(TaxAmount(9.15), taxesRoundRule.round(TaxAmount(9.17)))
        assertEquals(TaxAmount(9.20), taxesRoundRule.round(TaxAmount(9.18)))
        assertEquals(TaxAmount(9.20), taxesRoundRule.round(TaxAmount(9.19)))
        assertEquals(TaxAmount(2.35), taxesRoundRule.round(TaxAmount(2.374)))
        assertEquals(TaxAmount(2.80), taxesRoundRule.round(TaxAmount(2.799)))
        assertEquals(TaxAmount(1.40), taxesRoundRule.round(TaxAmount(1.3995)))
    }
}