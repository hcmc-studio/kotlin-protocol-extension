package studio.hcmc.kotlin.protocol.io

import kotlinx.serialization.Serializable
import studio.hcmc.kotlin.protocol.BitMaskFlag

interface ListOption<Filter : ListOptionFilter, Order : ListOptionOrder> : DataTransferObject {
    val filter: Filter
    val order: Order
}

interface ListOptionFilter : DataTransferObject {
    sealed interface Element {
        @JvmInline
        @Serializable
        value class IntEq(val value: Int) : Element

        @JvmInline
        @Serializable
        value class IntGreaterEq(val value: Int) : Element

        @JvmInline
        @Serializable
        value class IntLessEq(val value: Int) : Element

        @JvmInline
        @Serializable
        value class LongEq(val value: Long) : Element

        @JvmInline
        @Serializable
        value class LongGreaterEq(val value: Long) : Element

        @JvmInline
        @Serializable
        value class LongLessEq(val value: Long) : Element

        @JvmInline
        @Serializable
        value class StringEq(val value: String) : Element

        @JvmInline
        @Serializable
        value class StringLike(val value: String) : Element

        @JvmInline
        @Serializable
        value class StringNotLike(val value: String) : Element

        @JvmInline
        @Serializable
        value class DateEq(val value: String) : Element

        @JvmInline
        @Serializable
        value class DateGreaterEq(val value: String) : Element

        @JvmInline
        @Serializable
        value class DateLessEq(val value: String) : Element

        @JvmInline
        @Serializable
        value class BitMaskEnabled(val value: Int) : Element

        @JvmInline
        @Serializable
        value class BitMaskDisabled(val value: Int) : Element
    }
}

/**
 * 조회 정렬 순서
 */
interface ListOptionOrder : DataTransferObject