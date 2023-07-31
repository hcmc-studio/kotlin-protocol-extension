package studio.hcmc.kotlin.protocol

interface QualifiedValueObjectConvertor<QVO : QualifiedValueObject> {
    fun toQualifiedValueObject(): QVO
}

fun <QVO : QualifiedValueObject, QVOC : QualifiedValueObjectConvertor<QVO>> Iterable<QVOC>.toQualifiedValueObjects(): List<QVO> {
    return map { it.toQualifiedValueObject() }
}