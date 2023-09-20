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

    @Serializable
    data class NumericElement<T : Number>(
        val eq: T? = null,
        val neq: T? = null,
        val less: T? = null,
        val lessEq: T? = null,
        val greater: T? = null,
        val greaterEq: T? = null,
        val inList: List<T>? = null,
        val notInList: List<T>? = null
    ) : Element

    @Serializable
    data class DateElement(
        val eq: String? = null,
        val neq: String? = null,
        val less: String? = null,
        val lessEq: String? = null,
        val greater: String? = null,
        val greaterEq: String? = null,
        val inList: List<String>? = null,
        val notInList: List<String>? = null
    ) : Element

    @Serializable
    data class StringElement(
        val eq: String? = null,
        val neq: String? = null,
        val less: String? = null,
        val lessEq: String? = null,
        val greater: String? = null,
        val greaterEq: String? = null,
        val like: String? = null,
        val notLike: String? = null,
        val inList: List<String>? = null,
        val notInList: List<String>? = null
    ) : Element

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
         * field & [excludeAll] == field인 행만 검색
         */
        val excludeAll: BitMask<T>? = null,
        /**
         * field & [excludeAny] == 0 행만 검색
         */
        val excludeAny: BitMask<T>? = null
    ) : Element where T : BitMaskFlag, T : Enum<T>
}

/**
 * 조회 정렬 순서
 */
interface ListOptionOrder : DataTransferObject