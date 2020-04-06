package jp.kaleidot725.sample.model

interface TaskRepository {
    suspend fun add(task : Task) : Boolean
    suspend fun delete(task: Task) : Boolean
    suspend fun fetchTask(limit: Long) : List<Task>
}