package studio.hcmc.kotlin.protocol

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = BitMaskSerializer::class)
open class BitMask<E>(open val value: Int) : Number(), Comparable<BitMask<E>> where E : Enum<E>, E : BitMaskFlag {
    constructor(vararg flags: E): this(flags.fold(0) { acc, flag -> acc or flag.value })

    fun isEnabled(flag: E): Boolean {
        return value and flag.value != 0
    }

    fun isDisabled(flag: E): Boolean {
        return value and flag.value == 0
    }

    override fun compareTo(other: BitMask<E>): Int {
        return value.compareTo(other.value)
    }

    override fun toByte(): Byte {
        return value.toByte()
    }

    override fun toDouble(): Double {
        return value.toDouble()
    }

    override fun toFloat(): Float {
        return value.toFloat()
    }

    override fun toInt(): Int {
        return value
    }

    override fun toLong(): Long {
        return value.toLong()
    }

    override fun toShort(): Short {
        return value.toShort()
    }
}

private class BitMaskSerializer<E> : KSerializer<BitMask<E>> where E : Enum<E>, E : BitMaskFlag {
    override val descriptor = PrimitiveSerialDescriptor("BitMask", PrimitiveKind.INT)

    override fun deserialize(decoder: Decoder): BitMask<E> {
        return BitMask(decoder.decodeInt())
    }

    override fun serialize(encoder: Encoder, value: BitMask<E>) {
        encoder.encodeInt(value.value)
    }
}