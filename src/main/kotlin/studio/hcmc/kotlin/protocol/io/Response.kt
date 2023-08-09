package studio.hcmc.kotlin.protocol.io

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import studio.hcmc.kotlin.protocol.DataTransferObject

sealed interface Response<T> : DataTransferObject {
    val type: Type
    val metadata: Metadata
    val result: T

    @Serializable
    enum class Type {
        EMPTY, OBJECT, ARRAY, ERROR
    }

    @Serializable
    data class Metadata(
        val acceptedAt: Instant,
        val respondedAt: Instant = Clock.System.now()
    )

    @Serializable
    data class Empty(
        override val type: Type,
        override val metadata: Metadata,
        override val result: Unit
    ) : Response<Unit> {
        constructor(acceptedAt: Instant): this(
            type = Type.EMPTY,
            metadata = Metadata(acceptedAt),
            result = Unit
        )
    }

    @Serializable
    data class Object<T>(
        override val type: Type,
        override val metadata: Metadata,
        override val result: T
    ) : Response<T> {
        constructor(acceptedAt: Instant, result: T): this(
            type = Type.OBJECT,
            metadata = Metadata(acceptedAt),
            result = result
        )
    }

    @Serializable
    data class Array<T>(
        override val type: Type,
        override val metadata: Metadata,
        override val result: List<T>
    ) : Response<List<T>> {
        constructor(acceptedAt: Instant, result: List<T>): this(
            type = Type.ARRAY,
            metadata = Metadata(acceptedAt),
            result = result
        )
    }

    @Serializable
    data class Error(
        override val type: Type,
        override val metadata: Metadata,
        override val result: String,
        val className: String
    ) : Response<String> {
        constructor(acceptedAt: Instant, throwable: Throwable): this(
            type = Type.ERROR,
            metadata = Metadata(acceptedAt),
            result = throwable.stackTraceToString(),
            className = throwable::class.qualifiedName ?: "<unknown>"
        )
    }
}