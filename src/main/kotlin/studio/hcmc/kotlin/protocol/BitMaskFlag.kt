package studio.hcmc.kotlin.protocol

interface BitMaskFlag {
    companion object {
        fun <E> E.defaultValue(): Int where E : Enum<E>, E : BitMaskFlag {
            return 1 shl ordinal
        }
    }

    val value: Int

    infix fun <E> and(other: E): BitMask<E> where E : Enum<E>, E : BitMaskFlag {
        return BitMask(value and other.value)
    }

    infix fun <E> or(other: E): BitMask<E> where E : Enum<E>, E : BitMaskFlag {
        return BitMask(value or other.value)
    }

    infix fun <E> xor(other: E): BitMask<E> where E : Enum<E>, E : BitMaskFlag {
        return BitMask(value xor other.value)
    }
}