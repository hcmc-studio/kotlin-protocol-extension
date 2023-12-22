package studio.hcmc.kotlin.protocol.io

import kotlinx.serialization.Serializable

@Serializable
abstract class ErrorDataTransferObject : Throwable, ResponseDataTransferObject {
    abstract val httpStatusCode: Int

    constructor(): super()

    constructor(cause: Throwable?): super(cause)

    constructor(message: String?): super(message)

    constructor(message: String?, cause: Throwable?): super(message, cause)
}

@Serializable
open class SerializableThrowable(
    val originalClassName: String?,
    override val message: String?,
    override val cause: SerializableThrowable?,
    val stackTrace: List<SerializableStackTraceElement>
) : Throwable(message, cause) {
    constructor(throwable: Throwable): this(
        throwable::class.qualifiedName,
        throwable.message,
        throwable.cause?.let(::SerializableThrowable),
        throwable.stackTrace.map(::SerializableStackTraceElement)
    )
}

@Serializable
open class SerializableStackTraceElement(
    val fileName: String?,
    val lineNumber: Int,
    val moduleName: String?,
    val moduleVersion: String?,
    val classLoaderName: String?,
    val className: String?,
    val methodName: String?,
    val isNativeMethod: Boolean
) {
    constructor(element: StackTraceElement): this(
        element.fileName,
        element.lineNumber,
        element.moduleName,
        element.moduleVersion,
        element.classLoaderName,
        element.className,
        element.methodName,
        element.isNativeMethod
    )
}