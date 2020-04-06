package jp.kaleidot725.sample

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import jp.kaleidot725.sample.databinding.ActivityMainBinding
import jp.kaleidot725.sample.model.Task
import jp.kaleidot725.sample.model.TaskRepository
import jp.kaleidot725.sample.model.TaskRepositoryImpl
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.recycler_view_item.view.*

class MainActivity : AppCompatActivity() {
    private val repository: TaskRepository = TaskRepositoryImpl()
    private val viewModel = MainViewModel(repository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivityMainBinding =  DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel

        val adapter = TaskRecyclerAdapter()
        binding.taskRecyclerView.adapter = adapter
        binding.taskRecyclerView.layoutManager = LinearLayoutManager(applicationContext).apply {
            orientation = RecyclerView.VERTICAL
        }

        binding.taskNameEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.edit(s.toString())
            }
        })

        binding.addButton.setOnClickListener {
            viewModel.add()
            viewModel.refresh()
        }

        viewModel.tasks.observe(this, androidx.lifecycle.Observer {
            adapter.submitList(it)
        })
    }
}


class TaskRecyclerAdapter() : ListAdapter<Task, TaskHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false) as View
        return TaskHolder(view)
    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        holder.name.text = getItem(position)?.name
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Task>() {
            override fun areItemsTheSame(oldItem: Task, newItem: Task) =
                oldItem.id == newItem.id // check uniqueness

            override fun areContentsTheSame(oldItem: Task, newItem: Task) =
                oldItem == newItem // check contents
        }
    }
}

class TaskHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    val name = view.name
}

