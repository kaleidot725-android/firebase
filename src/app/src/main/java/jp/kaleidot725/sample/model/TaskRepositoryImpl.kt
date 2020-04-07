package jp.kaleidot725.sample.model

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import jp.kaleidot725.sample.model.toTask

class TaskRepositoryImpl : TaskRepository {
    private val database get() = FirebaseFirestore.getInstance()

    override suspend fun add(task: Task): Boolean {
        return try {
            database.collection(COLLECTION_PATH).document(task.id).set(task.toHashMap()).await()
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun delete(task: Task): Boolean {
        return try {
            database.collection(COLLECTION_PATH).document(task.id).delete().await()
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun fetchTask(limit: Long): List<Task> {
        return try {
            val query = database.collection(COLLECTION_PATH).limit(limit)
            val test = query.get().await()
            test.documents.map { it.data }.mapNotNull { it?.toTask() }
        } catch (e: Exception) {
            listOf()
        }
    }

    companion object {
        private const val COLLECTION_PATH = "tasks"
    }
}