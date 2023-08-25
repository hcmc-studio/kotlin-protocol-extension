package studio.hcmc.kotlin.protocol

/**
 * Isn't it too complex?
 */
interface IdHolder<Id : _Id<V>, V> {
    val id: Id
}