package studio.hcmc.kotlin.protocol

import kotlinx.datetime.Instant

interface Concealable : Creatable {
    override val createdAt: Instant
    val concealedAt: Instant?

    val isConcealed: Boolean get() = concealedAt != null
}