package jp.kaleidot725.sample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import jp.kaleidot725.sample.model.TaskRepository
import jp.kaleidot725.sample.model.TaskRepositoryImpl

class MainActivity : AppCompatActivity() {
    private val repository : TaskRepository = TaskRepositoryImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
