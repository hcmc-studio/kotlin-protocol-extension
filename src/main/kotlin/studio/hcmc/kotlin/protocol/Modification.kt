package studio.hcmc.kotlin.protocol

import kotlinx.datetime.Instant

interface Modification : Creatable {
    override val createdAt: Instant
}