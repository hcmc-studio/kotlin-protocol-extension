package studio.hcmc.kotlin.protocol

import kotlin.reflect.full.primaryConstructor

interface Id<V> {
    val value: V
}

inline fun <V, reified Id> V.id(): Id where Id : studio.hcmc.kotlin.protocol.Id<V> {
    return Id::class.primaryConstructor!!.call(this)
}