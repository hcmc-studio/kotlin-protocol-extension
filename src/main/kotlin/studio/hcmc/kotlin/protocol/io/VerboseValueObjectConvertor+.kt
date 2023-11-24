package studio.hcmc.kotlin.protocol.io

fun <VVO : VerboseValueObject, VVOC : VerboseValueObjectConverter<VVO>> Iterable<VVOC>.toVerboseValueObjects(): List<VVO> {
    return map { it.toVerboseValueObject() }
}