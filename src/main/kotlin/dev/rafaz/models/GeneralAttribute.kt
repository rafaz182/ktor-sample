package dev.rafaz.models

import kotlinx.serialization.Serializable

@Serializable
data class GeneralAttribute(
    val id: Int,
    val name: String,
    val description: String,
    val options: List<GeneralOption>
)
