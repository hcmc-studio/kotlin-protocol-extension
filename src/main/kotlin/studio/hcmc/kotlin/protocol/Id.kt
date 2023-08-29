package studio.hcmc.kotlin.protocol

import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

internal typealias _Id<V> = Id<V>

interface Id<V> {
    val value: V

    companion object {
        inline fun <V, reified Id : _Id<V>> wrap(value: V): Id {
            return wrap(value, Id::class)
        }

        fun <V, Id : _Id<V>> wrap(value: V, idClass: KClass<Id>): Id {
            return idClass.primaryConstructor!!.call(value)
        }
    }
}

interface IntId : Id<Int> {
    override val value: Int
}

interface LongId : Id<Long> {
    override val value: Long
}

interface StringId : Id<String> {
    override val value: String
}