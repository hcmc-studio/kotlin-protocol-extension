package studio.hcmc.kotlin.protocol

interface DataTransferObjectConsumer<DTO : DataTransferObject> {
    fun fromDataTransferObject(dto: DTO)
}