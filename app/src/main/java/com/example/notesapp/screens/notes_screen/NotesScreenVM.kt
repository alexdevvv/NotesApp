package com.example.notesapp.screens.notes_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notesapp.domain.model.Todo
import com.example.notesapp.domain.usecases.GetDataFromDbUseCase
import com.example.notesapp.domain.usecases.GetDataFromServerUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NotesScreenVM(
    private val getDataFromDbUseCase: GetDataFromDbUseCase,
    private val getDataFromServerUseCase: GetDataFromServerUseCase
) : ViewModel() {

    private var userId: Long? = null

    private val getTodosFromBDLiveData = MutableLiveData<MutableList<Todo>>()
    private val filteredTodosLiveData = MutableLiveData<MutableList<Todo>>()
    private val deleteTodoLiveData = MutableLiveData<String>()
    private val todosFromServerLiveData = MutableLiveData<List<Todo>>()
    private val disposable = CompositeDisposable()

    fun getTodosFromBDLiveData(): LiveData<MutableList<Todo>> = getTodosFromBDLiveData
    fun getDataDeleteTodo(): LiveData<String> = deleteTodoLiveData
    fun getFilterTodosLiveData(): LiveData<MutableList<Todo>> = filteredTodosLiveData
    fun getTodosFromServerLiveData(): LiveData<List<Todo>> = todosFromServerLiveData

    fun init(userId: Long) {
        this.userId = userId
        getTodosFromServer()
    }

    fun getTodosFromDb() {
        disposable.add(getDataFromDbUseCase.getTodosFromDbForCurrentUser(userId!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    getTodosFromBDLiveData.postValue(it as MutableList<Todo>?)
                }, {
                }
            )
        )
    }

    fun getTodosFromServer() {
        disposable.add(getDataFromServerUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    todosFromServerLiveData.postValue(it.todos)
                }, {
                    getTodosFromDb()
                }
            )
        )
    }

    fun deleteTodo(todo: Todo) {
        disposable.add(
            getDataFromDbUseCase.deleteTodo(todo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        deleteTodoLiveData.postValue("Заметка успешно удалена")
                        getTodosFromDb()

                    },
                    {
                        deleteTodoLiveData.postValue("Ошибка при удалении")
                    },
                )
        )
    }

    fun filterTodos(searchText: String) {
        if (searchText.isNotEmpty()) {
            getTodosFromBDLiveData.value?.let { databaseTodoList ->
                val filterTodoList = mutableListOf<Todo>()
                for (item in databaseTodoList) {
                    if (item.title.lowercase().contains(searchText.lowercase())) {
                        filterTodoList.add(item)
                    }
                }
                filteredTodosLiveData.value = filterTodoList
            }
        } else {
            filteredTodosLiveData.value = getTodosFromBDLiveData.value
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
        disposable.clear()
    }
}