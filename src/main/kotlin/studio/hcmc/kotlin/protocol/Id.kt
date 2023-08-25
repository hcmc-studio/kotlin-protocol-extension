package studio.hcmc.kotlin.protocol

import kotlin.reflect.full.primaryConstructor

private typealias _Id<V> = Id<V>

interface Id<V> {
    val value: V

    companion object {
        inline fun <V, reified Id : _Id<V>> wrap(value: V): Id {
            return Id::class.primaryConstructor!!.call(value)
        }
    }
}