package dev.rafaz.models

import kotlinx.serialization.Serializable

@Serializable
data class StockItem(
    val id: Int,
    val product: Product,
    val available: Int,
    val price: Double
)
