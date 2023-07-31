package studio.hcmc.kotlin.protocol

/**
 * @see org.jetbrains.exposed.sql.SortOrder
 */
enum class SortOrder(val code: String) {
    ASC(code = "ASC"),
    DESC(code = "DESC"),
    ASC_NULLS_FIRST(code = "ASC NULLS FIRST"),
    DESC_NULLS_FIRST(code = "DESC NULLS FIRST"),
    ASC_NULLS_LAST(code = "ASC NULLS LAST"),
    DESC_NULLS_LAST(code = "DESC NULLS LAST")
}