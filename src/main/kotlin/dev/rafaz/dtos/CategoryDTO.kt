package dev.rafaz.dtos

import kotlinx.serialization.Serializable

@Serializable
data class CategoryDTO(
    val parentId: Int?,
    val name: String,
    val attributesId: List<Int>?
)
