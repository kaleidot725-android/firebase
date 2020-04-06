package jp.kaleidot725.sample

import androidx.lifecycle.*
import jp.kaleidot725.sample.model.Task
import jp.kaleidot725.sample.model.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class MainViewModel(private val repository: TaskRepository) : ViewModel() {
    private val id: String get() = UUID.randomUUID().toString()
    private var name: String = ""
    private val time: Long get() = System.currentTimeMillis()

    private val _refresh: MutableLiveData<Unit> = MutableLiveData()

    val tasks: LiveData<List<Task>> = _refresh.switchMap {
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(repository.fetchTask(50))
        }
    }

    fun add() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.add(Task(id, time, name))
        }
    }

    fun edit(name: String) {
        this.name = name
    }

    fun refresh() {
        _refresh.postValue(null)
    }
}