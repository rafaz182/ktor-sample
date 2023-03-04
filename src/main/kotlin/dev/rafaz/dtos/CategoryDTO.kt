package dev.rafaz.dtos

import kotlinx.serialization.Serializable

@Serializable
data class CategoryDTO(
    val parentId: Int? = null,
    val name: String,
    val attributesId: List<Int>? = null
)
