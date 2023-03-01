package dev.rafaz.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import org.joda.time.LocalDateTime
import org.joda.time.format.ISODateTimeFormat

object LocalDateTimeIso8601Serializer : KSerializer<LocalDateTime> {
    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("LocalDateTime", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): LocalDateTime {
        val formatter = ISODateTimeFormat.localDateOptionalTimeParser()
        val raw = decoder.decodeString()
        return LocalDateTime.parse(raw, formatter)
    }

    override fun serialize(encoder: Encoder, value: LocalDateTime) {
        val dtStr = value.toString()
        encoder.encodeString(dtStr)
    }
}