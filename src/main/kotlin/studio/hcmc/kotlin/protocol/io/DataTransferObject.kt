package studio.hcmc.kotlin.protocol.io

interface DataTransferObject

/**
 * [DTO]에 정의된 값을 대상 객체에 적용
 */
inline fun <reified DTO> DTO.applyNotNullTo(o: Any): Int where DTO : DataTransferObject {
    var applied = 0
    val thisGetters = this::class.java.declaredMethods.filter { it.name.startsWith("get") }
    val destSetters = o::class.java.declaredMethods.filter { it.name.startsWith("set") }
    for (thisGetter in thisGetters) {
        if (!thisGetter.name.startsWith("get")) {
            continue
        }

        val setterName = "set${thisGetter.name.removePrefix("get")}"
        val v = thisGetter.invoke(this) ?: continue
        val setter = destSetters.find { it.name == setterName } ?: continue
        try {
            setter.invoke(o, v)
            applied++
        } catch (_: IllegalArgumentException) {}
    }

    return applied
}