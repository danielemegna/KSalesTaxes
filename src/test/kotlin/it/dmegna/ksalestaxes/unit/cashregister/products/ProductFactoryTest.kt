package it.dmegna.ksalestaxes.unit.cashregister.products

import it.dmegna.ksalestaxes.cashregister.products.*
import org.junit.Assert.*
import org.junit.Test
import kotlin.reflect.KClass

class ProductFactoryTest {

    private val productFactory = ProductFactory()

    @Test
    fun `generic products`() {
        "music CD" shouldBecomeA GenericProduct::class
        "bottle of perfume" shouldBecomeA GenericProduct::class
        "imported bottle of perfume" shouldBecomeA GenericProduct::class
    }

    @Test
    fun books() {
        "book" shouldBecomeA Book::class
        "Harry Potter and the Philosopher's Stone" shouldBecomeA Book::class
        "Clean Code: A Handbook of Agile Software Craftsmanship" shouldBecomeA Book::class
    }

    @Test
    fun `food products`() {
        "chocolate bar" shouldBecomeA FoodProduct::class
        "box of chocolates" shouldBecomeA FoodProduct::class
        "slice of pizza" shouldBecomeA FoodProduct::class
        "imported box of chocolates" shouldBecomeA FoodProduct::class
        "box of imported chocolates" shouldBecomeA FoodProduct::class
    }

    @Test
    fun `medical products`() {
        "packet of headache pills" shouldBecomeA MedicalProduct::class
        "headache pills" shouldBecomeA MedicalProduct::class
        "back pain pills" shouldBecomeA MedicalProduct::class
    }

    @Test
    fun `imported products description are normalized`() {
        "imported box of chocolates" shouldHaveDescription "box of chocolates"
        "box of imported chocolates" shouldHaveDescription "box of chocolates"
    }

    @Test
    fun `not imported products description are untouched`() {
        "book" shouldHaveDescription "book"
        "music CD" shouldHaveDescription "music CD"
        "bottle of perfume" shouldHaveDescription "bottle of perfume"
    }

    @Test
    fun `imported products flag`() {
        assertTrue(productFactory.from("imported box of chocolates").isImported)
        assertTrue(productFactory.from("imported bottle of perfume").isImported)
        assertTrue(productFactory.from("box of imported chocolates").isImported)
        assertFalse(productFactory.from("box of chocolates").isImported)
        assertFalse(productFactory.from("bottle of perfume").isImported)
    }

    private infix fun String.shouldBecomeA(expectedType: KClass<out Product>) {
        val product = productFactory.from(this)
        assertTrue("$product should become a ${expectedType.simpleName}", expectedType.isInstance(product))
    }

    private infix fun String.shouldHaveDescription(expectedDescription: String) {
        val product = productFactory.from(this)
        assertEquals(expectedDescription, product.description)
    }
}