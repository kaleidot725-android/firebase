package jp.kaleidot725.sample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = FirebaseFirestore.getInstance()
        val user = hashMapOf("first" to "Ada", "last" to "Lovelace", "born" to 1815)

        db.collection("users").add(user).addOnSuccessListener { documentReference ->
            Log.d("MainActivity", "DocumentSnapshot added with ID: ${documentReference.id}")
        }.addOnFailureListener { e ->
            Log.w("MainActivity", "Error adding document", e)
        }

        db.collection("users").get().addOnSuccessListener { result ->
            for (document in result) {
                Log.d("MainActivity", "${document.id} => ${document.data}")
            }
        }.addOnFailureListener { exception ->
            Log.w("MainActivity", "Error getting documents.", exception)
        }
    }
}
