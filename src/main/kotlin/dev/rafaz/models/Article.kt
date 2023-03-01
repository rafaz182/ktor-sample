package dev.rafaz.models

import dev.rafaz.serializers.LocalDateTimeIso8601Serializer
import kotlinx.serialization.Serializable
import org.joda.time.LocalDateTime

//https://stackoverflow.com/questions/67305843/kotlinx-serialization-deserializing-dates
//https://stackoverflow.com/questions/59952696/how-do-i-set-the-current-date-in-db-using-exposed-and-kotlin
//https://kotlinlang.org/api/kotlinx-datetime/kotlinx-datetime/kotlinx.datetime/to-local-date-time.html
//https://github.com/Kotlin/kotlinx-datetime#type-use-cases

@Serializable
data class Article(
    val id: Int,
    val title: String,
    val body: String,
    @Serializable(with = LocalDateTimeIso8601Serializer::class)
    val dateCreated: LocalDateTime
)