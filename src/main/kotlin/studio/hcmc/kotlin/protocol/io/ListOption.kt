package studio.hcmc.kotlin.protocol.io

import kotlinx.serialization.Serializable
import studio.hcmc.kotlin.protocol.BitMask
import studio.hcmc.kotlin.protocol.BitMaskFlag

interface ListOption<Filter : ListOptionFilter, Order : ListOptionOrder> : DataTransferObject {
    val filter: Filter
    val order: Order
}

interface ListOptionFilter : DataTransferObject {
    sealed interface Element

    sealed interface PrimitiveElement<T> : Element where T : Comparable<T> {
        val eq: T?
        val neq: T?
        val less: T?
        val lessEq: T?
        val greater: T?
        val greaterEq: T?
        val inList: List<T>?
        val notInList: List<T>?
    }

    @Serializable
    data class NumericElement<T>(
        override val eq: T? = null,
        override val neq: T? = null,
        override val less: T? = null,
        override val lessEq: T? = null,
        override val greater: T? = null,
        override val greaterEq: T? = null,
        override val inList: List<T>? = null,
        override val notInList: List<T>? = null
    ) : PrimitiveElement<T> where T : Number, T : Comparable<T>

    @Serializable
    data class CharElement(
        override val eq: Char? = null,
        override val neq: Char? = null,
        override val less: Char? = null,
        override val lessEq: Char? = null,
        override val greater: Char? = null,
        override val greaterEq: Char? = null,
        override val inList: List<Char>? = null,
        override val notInList: List<Char>? = null
    ) : PrimitiveElement<Char>

    @Serializable
    data class BooleanElement(
        val eq: Boolean? = null
    ) : Element

    @Serializable
    data class StringElement(
        override val eq: String? = null,
        override val neq: String? = null,
        override val less: String? = null,
        override val lessEq: String? = null,
        override val greater: String? = null,
        override val greaterEq: String? = null,
        val like: String? = null,
        val notLike: String? = null,
        override val inList: List<String>? = null,
        override val notInList: List<String>? = null
    ) : PrimitiveElement<String>

    @Serializable
    data class DateElement(
        override val eq: String? = null,
        override val neq: String? = null,
        override val less: String? = null,
        override val lessEq: String? = null,
        override val greater: String? = null,
        override val greaterEq: String? = null,
        override val inList: List<String>? = null,
        override val notInList: List<String>? = null
    ) : PrimitiveElement<String>

    @Serializable
    data class BitMaskElement<T>(
        /**
         * field == eq인 행만 검색
         */
        val eq: BitMask<T>? = null,
        /**
         * field != neq인 행만 검색
         */
        val neq: BitMask<T>? = null,
        /**
         * field & [includeAll] = [includeAll]인 행만 검색
         */
        val includeAll: BitMask<T>? = null,
        /**
         * field & [includeAny] != 0인 행만 검색
         */
        val includeAny: BitMask<T>? = null,
        /**
         * field & [excludeAll] == 0 행만 검색
         */
        val excludeAll: BitMask<T>? = null
    ) : Element where T : BitMaskFlag, T : Enum<T>

    @Serializable
    data class EnumElement<T>(
        val eq: T? = null,
        val neq: T? = null,
        val inList: List<T>? = null,
        val notInList: List<T>? = null
    ) : Element where T : Enum<T>
}

/**
 * 조회 정렬 순서
 */
interface ListOptionOrder : DataTransferObject