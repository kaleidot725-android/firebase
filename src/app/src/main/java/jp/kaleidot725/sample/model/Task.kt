package jp.kaleidot725.sample.model

data class Task(val id: String, val time: Long, val name: String, val checked: Boolean)

fun Task.toHashMap(): HashMap<String, *> {
    return hashMapOf(
        "id" to this.id,
        "time" to this.time,
        "name" to this.name,
        "checked" to this.checked
    )
}

fun HashMap<String, *>.toTask() : Task {
    val id = this["id"] as String
    val time = this["time"] as Long
    val name = this["name"] as String
    val checked = this["checked"] as Boolean
    return Task(id, time, name, checked)
}