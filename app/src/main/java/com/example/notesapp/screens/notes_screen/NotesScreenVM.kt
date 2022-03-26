package com.example.notesapp.screens.notes_screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.domain.model.ModelTodo
import com.example.notesapp.domain.usecases.GetDataFromDbUseCase
import com.example.notesapp.domain.usecases.GetDataFromServerUseCase
import com.example.notesapp.domain.usecases.OverrideDatabaseUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NotesScreenVM(
    private val getDataFromDbUseCase: GetDataFromDbUseCase,
    private val getDataFromServerUseCase: GetDataFromServerUseCase,
    private val overrideDatabaseUseCase: OverrideDatabaseUseCase
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
                    Log.e("Что хранится в базе", it.size.toString())
                }, {
                }
            )
        )
    }

    fun getTodosFromServer() {
        disposable.add(getDataFromServerUseCase.getTodosFromServer()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    todosFromServerLiveData.postValue(it)
                    GlobalScope.launch {
                        overrideDatabaseUseCase.overrideTodosTable(it)
                        Log.e("listSize", it.size.toString())
                    }

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