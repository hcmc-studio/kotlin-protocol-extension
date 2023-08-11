package studio.hcmc.kotlin.protocol

import kotlinx.serialization.Serializable

@Serializable
abstract class ErrorDataTransferObject : Throwable(), DataTransferObject {
    abstract val httpStatusCode: Int
}