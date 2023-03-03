package dev.rafaz.models

import kotlinx.serialization.Serializable

@Serializable
data class Customer(
    val user: User,
    val firstName: String,
    val lastName: String,
    val favoriteProducts: List<Product>
)