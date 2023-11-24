package studio.hcmc.kotlin.protocol.io

interface VerboseValueObjectConverter<VVO : VerboseValueObject> {
    fun toVerboseValueObject(): VVO
}