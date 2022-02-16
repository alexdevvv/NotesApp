package com.example.notesapp.screens.notes_screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notesapp.domain.model.Todo
import com.example.notesapp.domain.usecases.GetFromDbUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NotesScreenVM(private val getFromDbUseCase: GetFromDbUseCase) : ViewModel() {

    private val getTodosLiveData = MutableLiveData<List<Todo>>()
    private val liveDataDeleteTodo = MutableLiveData<String>()
    private val disposable = CompositeDisposable()
    fun getTodosLiveData(): LiveData<List<Todo>> = getTodosLiveData



    fun getTodosFromDb() {
        disposable.add(getFromDbUseCase.getTodosFromDb()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    getTodosLiveData.postValue(it)
                }, {
                    Log.e("XXX", it.message.toString())
                }
            )
        )
    }

    fun deleteTodo(todo: Todo) {
        disposable.add(getFromDbUseCase.deleteTodo(todo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                liveDataDeleteTodo.postValue("Заметка успешно удалена")
            }, {
                liveDataDeleteTodo.postValue("Ошибка при удалении")
            }
            ))
    }


    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
        disposable.clear()
    }
}