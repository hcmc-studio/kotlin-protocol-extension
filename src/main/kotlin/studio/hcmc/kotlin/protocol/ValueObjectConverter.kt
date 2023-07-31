package studio.hcmc.kotlin.protocol

interface ValueObjectConverter<VO : ValueObject> {
    fun toValueObject(): VO
}


fun <VO : ValueObject, VOC : ValueObjectConverter<VO>> Iterable<VOC>.toValueObjects(): List<VO> {
    return map { it.toValueObject() }
}