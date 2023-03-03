package dev.rafaz.models

import kotlinx.serialization.Serializable

@Serializable
data class Address(
    val customer: Customer
)
