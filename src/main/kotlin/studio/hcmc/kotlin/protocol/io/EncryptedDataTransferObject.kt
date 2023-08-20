package studio.hcmc.kotlin.protocol.io

import kotlinx.serialization.Serializable

@Serializable
data class EncryptedDataTransferObject(
    val publicKey: String,
    val body: String
) : DataTransferObject