package dev.rafaz.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormatter
import org.joda.time.format.DateTimeFormatterBuilder
import org.joda.time.format.ISODateTimeFormat

object DateTimeIso8601Serializer : KSerializer<DateTime> {
    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("DateTime", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): DateTime {
        val formatter = ISODateTimeFormat.dateTimeNoMillis()
        val raw = decoder.decodeString()
        return DateTime.parse(raw, formatter)
    }

    override fun serialize(encoder: Encoder, value: DateTime) {
        val formatter = ISODateTimeFormat.dateTimeNoMillis()
        val dtStr = value.toString(formatter)
        encoder.encodeString(dtStr)
    }
}