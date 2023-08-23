package studio.hcmc.kotlin.protocol.io

import kotlinx.datetime.Clock
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun main() {
    val encoded = Json.encodeToString(Response.Empty(Clock.System.now()))
    println(encoded)
}