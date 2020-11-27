package it.dmegna.ksalestaxes.products

sealed class Product(val description: String)
class GenericProduct(description: String) : Product(description)
class Book(description: String) : Product(description)
class FoodProduct(description: String) : Product(description)
class MedicalProduct(description: String) : Product(description)