package it.dmegna.ksalestaxes.unit.taxes

import it.dmegna.ksalestaxes.products.Book
import it.dmegna.ksalestaxes.products.FoodProduct
import it.dmegna.ksalestaxes.products.GenericProduct
import it.dmegna.ksalestaxes.products.MedicalProduct
import it.dmegna.ksalestaxes.taxes.HappyLandTaxRules
import it.dmegna.ksalestaxes.taxes.TaxRules
import it.dmegna.ksalestaxes.taxes.data.TaxRate
import org.junit.Assert.assertEquals
import org.junit.Test

class HappyLandTaxRulesTest {

    private val taxRules: TaxRules = HappyLandTaxRules()

    @Test
    fun `generic products tax rate`() {
        val product = GenericProduct("product description")
        val actual = taxRules.getTaxRateFor(product)
        assertEquals(TaxRate(0.10), actual)
    }

    @Test
    fun `books, food, and medical products are exempt`() {
        assertEquals(TaxRate(0.0), taxRules.getTaxRateFor(Book("title")))
        assertEquals(TaxRate(0.0), taxRules.getTaxRateFor(FoodProduct("name")))
        assertEquals(TaxRate(0.0), taxRules.getTaxRateFor(MedicalProduct("name")))
    }

    @Test
    fun `imported tax exempt products`() {
        assertEquals(TaxRate(0.05), taxRules.getTaxRateFor(Book("title", isImported = true)))
        assertEquals(TaxRate(0.05), taxRules.getTaxRateFor(FoodProduct("name", isImported = true)))
        assertEquals(TaxRate(0.05), taxRules.getTaxRateFor(MedicalProduct("name", isImported = true)))
    }

    @Test
    fun `imported generic products`() {
        val product = GenericProduct("product description", isImported = true)
        val actual = taxRules.getTaxRateFor(product)
        assertEquals(TaxRate(0.15), actual)
    }

}