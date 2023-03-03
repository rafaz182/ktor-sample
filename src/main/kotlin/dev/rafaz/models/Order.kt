package dev.rafaz.models

import dev.rafaz.serializers.LocalDateTimeIso8601Serializer
import kotlinx.serialization.Serializable
import org.joda.time.LocalDateTime

@Serializable
data class Order(
    val id: Int,
    val customer: Customer,
    @Serializable(with = LocalDateTimeIso8601Serializer::class)
    val created: LocalDateTime,
    val deliveryAddress: Address
)
