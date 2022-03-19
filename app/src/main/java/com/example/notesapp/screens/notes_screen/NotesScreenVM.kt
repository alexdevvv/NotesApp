package com.example.notesapp.screens.notes_screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notesapp.domain.model.ModelTodo
import com.example.notesapp.domain.usecases.GetDataFromDbUseCase
import com.example.notesapp.domain.usecases.GetDataFromServerUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NotesScreenVM(
    private val getDataFromDbUseCase: GetDataFromDbUseCase,
    private val getDataFromServerUseCase: GetDataFromServerUseCase,
) : ViewModel() {

    private var userId: Long? = null

    private val getTodosFromBDLiveData = MutableLiveData<MutableList<ModelTodo>>()
    private val filteredTodosLiveData = MutableLiveData<MutableList<ModelTodo>>()
    private val deleteTodoLiveData = MutableLiveData<String>()
    private val todosFromServerLiveData = MutableLiveData<List<ModelTodo>>()
    private val disposable = CompositeDisposable()

    fun getTodosFromBDLiveData(): LiveData<MutableList<ModelTodo>> = getTodosFromBDLiveData
    fun getDataDeleteTodo(): LiveData<String> = deleteTodoLiveData
    fun getFilterTodosLiveData(): LiveData<MutableList<ModelTodo>> = filteredTodosLiveData
    fun getTodosFromServerLiveData(): LiveData<List<ModelTodo>> = todosFromServerLiveData

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
                    getTodosFromBDLiveData.postValue(it as MutableList<ModelTodo>?)
                }, {
                }
            )
        )
    }

    private fun getTodosFromServer() {
        disposable.add(getDataFromServerUseCase.getTodosFromDb()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    todosFromServerLiveData.postValue(it)
                }, {
                    getTodosFromDb()
                }
            )
        )
    }

    fun deleteTodoFromDb(modelTodo: ModelTodo) {
        disposable.add(
            getDataFromDbUseCase.deleteTodo(modelTodo)
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

    fun deleteTodoFromServer(todoId: Long) {
        disposable.add(
            getDataFromServerUseCase.deleteTodo(todoId = todoId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    getTodosFromServer()
                }, {
                    Log.e("XXX", it.message.toString())
                }
                )
        )
    }

    fun filterTodos(searchText: String) {
        if (searchText.isNotEmpty()) {
            getTodosFromBDLiveData.value?.let { databaseTodoList ->
                val filterTodoList = mutableListOf<ModelTodo>()
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