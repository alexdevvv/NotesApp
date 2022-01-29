package com.example.notesapp.screens.new_todo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notesapp.domain.model.Todo
import com.example.notesapp.domain.usecases.GetFromDbUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NewTodoScreenVM(private val getFromDbUseCase: GetFromDbUseCase): ViewModel() {

    private val disposable = CompositeDisposable()
    private val liveDataInsertTodo = MutableLiveData<String>()

    fun insertTodo(todo: Todo) {
        disposable.add(getFromDbUseCase.insertTodo(todo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                liveDataInsertTodo.postValue("Заметка успешно добавлена")
            }, {
                liveDataInsertTodo.postValue("Произошла ошибка")
            }))
    }
}