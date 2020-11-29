package it.dmegna.ksalestaxes.unit.cashregister.taxes

import it.dmegna.ksalestaxes.cashregister.products.Book
import it.dmegna.ksalestaxes.cashregister.products.FoodProduct
import it.dmegna.ksalestaxes.cashregister.products.GenericProduct
import it.dmegna.ksalestaxes.cashregister.products.MedicalProduct
import it.dmegna.ksalestaxes.cashregister.taxes.HappyLandTaxRules
import it.dmegna.ksalestaxes.cashregister.taxes.TaxRules
import it.dmegna.ksalestaxes.cashregister.taxes.data.TaxRate
import org.junit.Assert.assertEquals
import org.junit.Test

class HappyLandTaxRulesTest {

    private val taxRules: TaxRules = HappyLandTaxRules()

    @Test
    fun `generic products tax rate`() {
        val product = GenericProduct("product description")
        val actual = taxRules.getTaxRateFor(product)
        assertEquals(TaxRate.of(0.10), actual)
    }

    @Test
    fun `books, food, and medical products are exempt`() {
        assertEquals(TaxRate.of(0.0), taxRules.getTaxRateFor(Book("title")))
        assertEquals(TaxRate.of(0.0), taxRules.getTaxRateFor(FoodProduct("name")))
        assertEquals(TaxRate.of(0.0), taxRules.getTaxRateFor(MedicalProduct("name")))
    }

    @Test
    fun `imported tax exempt products`() {
        assertEquals(TaxRate.of(0.05), taxRules.getTaxRateFor(Book("title", isImported = true)))
        assertEquals(TaxRate.of(0.05), taxRules.getTaxRateFor(FoodProduct("name", isImported = true)))
        assertEquals(TaxRate.of(0.05), taxRules.getTaxRateFor(MedicalProduct("name", isImported = true)))
    }

    @Test
    fun `imported generic products`() {
        val product = GenericProduct("product description", isImported = true)
        val actual = taxRules.getTaxRateFor(product)
        assertEquals(TaxRate.of(0.15), actual)
    }

}