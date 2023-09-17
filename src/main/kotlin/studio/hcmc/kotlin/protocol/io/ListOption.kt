package studio.hcmc.kotlin.protocol.io

interface ListOption<Filter : ListOptionFilter, Order : ListOptionOrder> : DataTransferObject {
    val filter: Filter
    val order: Order
}

interface ListOptionFilter : DataTransferObject

/**
 * 조회 정렬 순서
 */
interface ListOptionOrder : DataTransferObject