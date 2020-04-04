package jp.kaleidot725.sample.model

interface TaskRepository {
    suspend fun add(task : Task) : Boolean
    suspend fun delete(task: Task) : Boolean
    suspend fun fetchTask(afterTime: Long, limit: Long) : List<Task>
}