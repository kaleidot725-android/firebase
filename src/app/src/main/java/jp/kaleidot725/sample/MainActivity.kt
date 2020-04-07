package jp.kaleidot725.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import jp.kaleidot725.sample.model.Task
import jp.kaleidot725.sample.model.TaskRepository
import jp.kaleidot725.sample.model.TaskRepositoryImpl
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity() {
    private val repository: TaskRepository = TaskRepositoryImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            // ①現在のタスク一覧を取得する
            println("①現在のタスク一覧を取得する ▶ " + repository.fetchTask(100))

            // ②新しいタスクを追加する
            val date = Date()
            val newTask = Task(date.toString(), date.time, "New Task")
            repository.add(newTask)
            println("①新しいタスクを追加する ▶ " + repository.fetchTask(100))

            // ③新しく追加したタスクを削除する
            repository.delete(newTask)
            println("③新しく追加したタスクを削除する ▶ " + repository.fetchTask(100))
        }
    }
}
