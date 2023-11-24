package studio.hcmc.kotlin.protocol

import kotlinx.datetime.Instant

interface Modifiable {
    val lastModifiedAt: Instant?

    val isModified: Boolean get() = lastModifiedAt != null
}