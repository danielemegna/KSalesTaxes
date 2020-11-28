package it.dmegna.ksalestaxes.unit.products

import it.dmegna.ksalestaxes.products.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
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
    fun `keeps descriptions`() {
        assertEquals("book", productFactory.from("book").description)
        assertEquals("music CD", productFactory.from("music CD").description)
        assertEquals("bottle of perfume", productFactory.from("bottle of perfume").description)
    }

    @Test
    fun `imported products`() {
        assertEquals(
            FoodProduct("imported box of chocolates", isImported = true),
            productFactory.from("imported box of chocolates")
        )
        assertEquals(
            GenericProduct("imported bottle of perfume", isImported = true),
            productFactory.from("imported bottle of perfume")
        )
        assertEquals(
            FoodProduct("box of imported chocolates", isImported = true),
            productFactory.from("box of imported chocolates")
        )
    }

    private infix fun String.shouldBecomeA(kClass: KClass<out Product>) {
        val product = productFactory.from(this)
        assertTrue("$product should become a ${kClass.simpleName}", kClass.isInstance(product))
    }
}