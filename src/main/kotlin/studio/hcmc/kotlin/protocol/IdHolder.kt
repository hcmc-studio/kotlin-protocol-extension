package studio.hcmc.kotlin.protocol

/**
 * Isn't it too complex?
 */
interface IdHolder<Id : _Id<V>, V> {
    val id: Id
}

interface IntIdHolder<Id : _Id<Int>> : IdHolder<Id, Int> {
    override val id: Id
}

interface LongIdHolder<Id : _Id<Long>> : IdHolder<Id, Long> {
    override val id: Id
}

interface StringIdHolder<Id : _Id<String>> : IdHolder<Id, String> {
    override val id: Id
}