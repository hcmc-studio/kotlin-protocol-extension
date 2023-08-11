package studio.hcmc.kotlin.protocol

open class MutableBitMask<E> : BitMask<E> where E : Enum<E>, E : BitMaskFlag {
    override var value: Int = super.value

    constructor(initial: Int): super(initial)

    constructor(vararg flags: E): super(*flags)

    operator fun set(key: E, value: Boolean): Boolean {
        val prev = isEnabled(key)
        if (value) {
            enable(key)
        } else {
            disable(key)
        }

        return prev
    }

    fun enable(flag: E) {
        value = value or flag.value
    }

    fun enableIf(flag: E, condition: Boolean) {
        enableIf(flag) { condition }
    }

    inline fun enableIf(flag: E, predicate: MutableBitMask<E>.() -> Boolean) {
        if (predicate()) {
            enable(flag)
        }
    }

    fun disable(flag: E) {
        value = value xor flag.value
    }

    fun disable(flag: E, condition: Boolean) {
        disableIf(flag) { condition }
    }

    inline fun disableIf(flag: E, predicate: MutableBitMask<E>.() -> Boolean) {
        if (predicate()) {
            disable(flag)
        }
    }

    fun inverse(flag: E) {
        this[flag] = isDisabled(flag)
    }
}