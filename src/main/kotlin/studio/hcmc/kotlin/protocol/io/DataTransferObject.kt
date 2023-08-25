package studio.hcmc.kotlin.protocol.io

import studio.hcmc.kotlin.protocol.Id
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties

interface DataTransferObject

/**
 * [DTO]에 정의된 값을 대상 객체에 적용
 */
@Suppress("UNCHECKED_CAST")
inline fun <reified DTO, reified Destination : Any> DTO.applyNotNullTo(
    o: Destination,
    vararg excludes: String
): Int where DTO : DataTransferObject {
    var applied = 0
    val thisGetters = this::class.memberProperties
        .filterIsInstance<KProperty1<DTO, *>>()
        .filter { property -> excludes.none { it == property.name } }
    val destSetters = o::class.memberProperties
        .filterIsInstance<KMutableProperty1<Destination, *>>()
        .filter { property -> excludes.none { it == property.name } }
    for (thisGetter in thisGetters) {
        val v = thisGetter.get(this) ?: continue
        val destSetter = destSetters.find { it.name == thisGetter.name } ?: continue
        if (v is Id<*>) {
            (destSetter as KMutableProperty1<Destination, Any?>).set(o, v.value)
        } else {
            (destSetter as KMutableProperty1<Destination, Any?>).set(o, v)
        }

        applied++
    }

    return applied
}