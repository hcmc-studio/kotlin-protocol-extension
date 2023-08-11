package studio.hcmc.kotlin.protocol

import kotlinx.serialization.Serializable

@Serializable
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