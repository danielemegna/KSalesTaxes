package it.dmegna.ksalestaxes.cashregister.products

sealed class Product(open val description: String, open val isImported: Boolean)

data class GenericProduct(
    override val description: String,
    override val isImported: Boolean = false
) : Product(description, isImported)

data class Book(
    override val description: String,
    override val isImported: Boolean = false
) : Product(description, isImported)

data class FoodProduct(
    override val description: String,
    override val isImported: Boolean = false
) : Product(description, isImported)

data class MedicalProduct(
    override val description: String,
    override val isImported: Boolean = false
) : Product(description, isImported)