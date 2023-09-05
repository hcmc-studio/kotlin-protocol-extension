package studio.hcmc.kotlin.protocol.io

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

sealed interface Response<T> : DataTransferObject {
    val type: Type
    val metadata: Metadata
    val result: T

    @Serializable
    enum class Type {
        Empty, Object, Array, Error
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
    ) : Response<Unit> {
        @Transient
        override val result: Unit = Unit

        constructor(acceptedAt: Instant): this(
            type = Type.Empty,
            metadata = Metadata(acceptedAt)
        )
    }

    @Serializable
    data class Object<T>(
        override val type: Type,
        override val metadata: Metadata,
        override val result: T
    ) : Response<T> {
        constructor(acceptedAt: Instant, result: T): this(
            type = Type.Object,
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
            type = Type.Array,
            metadata = Metadata(acceptedAt),
            result = result
        )
    }

    @Serializable
    data class Error(
        override val type: Type,
        override val metadata: Metadata,
        override val result: String,
        val className: String,
        val status: Int
    ) : Response<String> {
        constructor(acceptedAt: Instant, throwable: Throwable): this(
            type = Type.Error,
            metadata = Metadata(acceptedAt),
            result = throwable.stackTraceToString(),
            className = throwable::class.qualifiedName ?: "<unknown>",
            status = if (throwable is ErrorDataTransferObject) throwable.httpStatusCode else 500
        )
    }
}