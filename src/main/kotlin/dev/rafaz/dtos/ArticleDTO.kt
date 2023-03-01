package dev.rafaz.dtos

import kotlinx.serialization.Serializable

@Serializable
data class ArticleDTO(val title: String, val body: String)
