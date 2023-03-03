package dev.rafaz.models

data class LineItem(
    val id: Int,
    val order: Order,
    val stockItem: StockItem,
    val quantity: Int
)
