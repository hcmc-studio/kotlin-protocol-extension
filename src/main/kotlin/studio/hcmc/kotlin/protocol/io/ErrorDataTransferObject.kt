package studio.hcmc.kotlin.protocol.io

import kotlinx.serialization.Serializable

@Serializable
abstract class ErrorDataTransferObject : Throwable(), DataTransferObject {
    abstract val httpStatusCode: Int
}