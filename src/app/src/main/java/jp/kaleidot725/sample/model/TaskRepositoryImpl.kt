package jp.kaleidot725.sample.model

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class TaskRepositoryImpl : TaskRepository {
    private val database get() = FirebaseFirestore.getInstance()

    override suspend fun add(task: Task): Boolean {
        try {
            database.collection(COLLECTION_PATH).document(task.id).update(task.toHashMap()).await()
            return true
        } catch (e : Exception) {
            return false
        }
    }

    override suspend fun delete(task: Task) : Boolean {
        try {
            database.collection(COLLECTION_PATH).document(task.id).delete().await()
            return true
        } catch (e: Exception) {
            return false
        }
    }

    override suspend fun fetchTask(afterTime: Long, limit: Long): List<Task> {
        try {
            return database.collection(COLLECTION_PATH).whereGreaterThanOrEqualTo("time", afterTime).get().await().map {
                it.data.toTask()
            }
        } catch (e: Exception) {
            return listOf()
        }
    }

    companion object {
        private const val COLLECTION_PATH = "tasks"
    }
}