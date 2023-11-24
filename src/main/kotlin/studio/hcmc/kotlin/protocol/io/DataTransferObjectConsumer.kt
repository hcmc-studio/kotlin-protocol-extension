package studio.hcmc.kotlin.protocol.io

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

interface DataTransferObjectConsumer<DTO : DataTransferObject> {
    fun fromDataTransferObject(dto: DTO, now: Instant = Clock.System.now())
}