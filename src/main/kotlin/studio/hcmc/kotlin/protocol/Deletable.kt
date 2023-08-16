package studio.hcmc.kotlin.protocol

import kotlinx.datetime.Instant

interface Deletable {
    val deletedAt: Instant?

    val isDeleted: Boolean get() = deletedAt != null
}