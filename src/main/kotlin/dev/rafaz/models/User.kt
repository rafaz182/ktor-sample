package dev.rafaz.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val password: String
)
