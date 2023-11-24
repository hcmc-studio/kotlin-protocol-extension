package studio.hcmc.kotlin.protocol

import kotlinx.datetime.Instant

@Deprecated("Use Concealable instead")
interface Deletable {
    val deletedAt: Instant?

    val isDeleted: Boolean get() = deletedAt != null
}