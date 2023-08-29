package studio.hcmc.kotlin.protocol.io

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialKind
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

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
    ) : Response<Unit> {
        @Transient
        override val result: Unit = Unit

        constructor(acceptedAt: Instant): this(
            type = Type.EMPTY,
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
        val className: String,
        val status: Int
    ) : Response<String> {
        constructor(acceptedAt: Instant, throwable: Throwable): this(
            type = Type.ERROR,
            metadata = Metadata(acceptedAt),
            result = throwable.stackTraceToString(),
            className = throwable::class.qualifiedName ?: "<unknown>",
            status = if (throwable is ErrorDataTransferObject) throwable.httpStatusCode else 500
        )
    }
}