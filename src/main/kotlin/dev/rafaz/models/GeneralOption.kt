package dev.rafaz.models

import kotlinx.serialization.Serializable

@Serializable
data class GeneralOption(
    val id: Int,
    val value: String
)
