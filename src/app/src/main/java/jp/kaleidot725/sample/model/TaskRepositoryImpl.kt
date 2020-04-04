package jp.kaleidot725.sample.model

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class TaskRepositoryImpl : TaskRepository {
    private val database get() = FirebaseFirestore.getInstance()

    override suspend fun add(task: Task): Boolean {
        try {
            database.collection(COLLECTION_PATH).document(task.id).(task.toHashMap()).await()
            return true
        } catch (e : Exception) {
            return false
        }
    }

    override suspend fun delete(task: Task) : Boolean {
        try {
            database.collection(COLLECTION_PATH).(task.toHashMap()).await()
            return true
        } catch (e: Exception) {
            return false
        }
    }

    override suspend fun fetchTask(afterTime: Long, limit: Long): List<Task> {
        TODO("Not yet implemented")
    }

    companion object {
        private val COLLECTION_PATH = "tasks"
    }
}