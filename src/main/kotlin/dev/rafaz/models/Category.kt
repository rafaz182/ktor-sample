package dev.rafaz.models

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val id: Int,
    val parent: Category?,
    val name: String,
    val attributes: List<GeneralAttribute>
)
