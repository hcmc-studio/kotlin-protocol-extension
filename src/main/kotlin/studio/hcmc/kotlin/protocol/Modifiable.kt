package studio.hcmc.kotlin.protocol

import kotlinx.datetime.Instant

interface Modifiable : Creatable {
    override val createdAt: Instant
    val lastModifiedAt: Instant?

    val isModified: Boolean get() = lastModifiedAt != null
}