package com.example.notesapp.screens.notes_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notesapp.domain.model.Todo
import com.example.notesapp.domain.usecases.GetFromDbUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NotesScreenVM(private val getFromDbUseCase: GetFromDbUseCase) : ViewModel() {

    private val getTodosLiveData = MutableLiveData<MutableList<Todo>>()
    private val filteredTodosLiveData = MutableLiveData<MutableList<Todo>>()
    private val liveDataDeleteTodo = MutableLiveData<String>()
    private val disposable = CompositeDisposable()

    fun getTodosLiveData(): LiveData<MutableList<Todo>> = getTodosLiveData
    fun getDataDeleteTodo(): LiveData<String> = liveDataDeleteTodo
    fun getFilterTodosLiveData(): LiveData<MutableList<Todo>> = filteredTodosLiveData

    fun getTodosFromDb(userId: Long) {
        disposable.add(getFromDbUseCase.getTodosFromDbForCurrentUser(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    getTodosLiveData.postValue(it as MutableList<Todo>?)
                }, {
                }
            )
        )
    }

    fun deleteTodo(todo: Todo) {
        disposable.add(
            getFromDbUseCase.deleteTodo(todo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        liveDataDeleteTodo.postValue("Заметка успешно удалена")
                    },
                    {
                        liveDataDeleteTodo.postValue("Ошибка при удалении")
                    },
                )
        )
    }

    fun filterTodos(searchText: String) {
        if (searchText.isNotEmpty()) {
            getTodosLiveData.value?.let { databaseTodoList ->
                val filterTodoList = mutableListOf<Todo>()
                for (item in databaseTodoList) {
                    if (item.title.lowercase().contains(searchText.lowercase())) {
                        filterTodoList.add(item)
                    }
                }
                filteredTodosLiveData.value = filterTodoList
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
        disposable.clear()
    }
}