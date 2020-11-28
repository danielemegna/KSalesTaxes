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
        assertEquals(TaxAmount.of(1.0), taxesRoundRule.round(TaxAmount.of(1.0)))
        assertEquals(TaxAmount.of(0.5), taxesRoundRule.round(TaxAmount.of(0.5)))
        assertEquals(TaxAmount.of(0.05), taxesRoundRule.round(TaxAmount.of(0.05)))
        assertEquals(TaxAmount.of(7.05), taxesRoundRule.round(TaxAmount.of(7.05)))
        assertEquals(TaxAmount.of(19.85), taxesRoundRule.round(TaxAmount.of(19.85)))
    }

    @Test
    fun `round up to the nearest 0,05`() {
        assertEquals(TaxAmount.of(9.10), taxesRoundRule.round(TaxAmount.of(9.11)))
        assertEquals(TaxAmount.of(9.15), taxesRoundRule.round(TaxAmount.of(9.125)))
        assertEquals(TaxAmount.of(9.15), taxesRoundRule.round(TaxAmount.of(9.13)))
        assertEquals(TaxAmount.of(9.15), taxesRoundRule.round(TaxAmount.of(9.16)))
        assertEquals(TaxAmount.of(9.15), taxesRoundRule.round(TaxAmount.of(9.17)))
        assertEquals(TaxAmount.of(9.20), taxesRoundRule.round(TaxAmount.of(9.18)))
        assertEquals(TaxAmount.of(9.20), taxesRoundRule.round(TaxAmount.of(9.19)))
        assertEquals(TaxAmount.of(9.35), taxesRoundRule.round(TaxAmount.of(9.374)))
        assertEquals(TaxAmount.of(9.40), taxesRoundRule.round(TaxAmount.of(9.3995)))
        assertEquals(TaxAmount.of(9.40), taxesRoundRule.round(TaxAmount.of(9.3995)))
        assertEquals(TaxAmount.of(9.80), taxesRoundRule.round(TaxAmount.of(9.799)))
    }
}