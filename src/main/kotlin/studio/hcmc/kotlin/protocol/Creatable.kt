package studio.hcmc.kotlin.protocol

import kotlinx.datetime.Instant

interface Creatable {
    val createdAt: Instant
}