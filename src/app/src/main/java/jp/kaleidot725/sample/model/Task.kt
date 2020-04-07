package jp.kaleidot725.sample.model

import java.util.*
import kotlin.collections.HashMap

data class Task(val id: String, val time: Long, val name: String) {
    companion object {
        fun generateId() : String = UUID.randomUUID().toString()
    }
}

fun Task.toHashMap(): HashMap<String, *> {
    return hashMapOf(
        "id" to this.id,
        "time" to this.time,
        "name" to this.name
    )
}

fun Map<String, Any>.toTask() : Task {
    val id = this["id"] as String
    val time = this["time"] as Long
    val name = this["name"] as String
    return Task(id, time, name)
}