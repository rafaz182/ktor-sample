package dev.rafaz.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val email: String,
    val password: String,
    val nick: String
)
