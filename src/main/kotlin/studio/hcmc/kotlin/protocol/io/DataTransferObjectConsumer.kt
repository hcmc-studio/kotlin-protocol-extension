package studio.hcmc.kotlin.protocol.io

interface DataTransferObjectConsumer<DTO : DataTransferObject> {
    fun fromDataTransferObject(dto: DTO)
}