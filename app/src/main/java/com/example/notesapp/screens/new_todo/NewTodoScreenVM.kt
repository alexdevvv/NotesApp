package com.example.notesapp.screens.new_todo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notesapp.domain.model.ModelGetTodoFromServer
import com.example.notesapp.domain.model.ModelSendNewTodoToServer
import com.example.notesapp.domain.model.Todo
import com.example.notesapp.domain.usecases.AddNewTodoUseCase
import com.example.notesapp.domain.usecases.GetFromDbUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NewTodoScreenVM(
    private val getFromDbUseCase: GetFromDbUseCase,
    private val addNewTodoUseCase: AddNewTodoUseCase
) : ViewModel() {

    private val disposable = CompositeDisposable()

    fun insertTodoInDatabase(todo: Todo) {
        disposable.add(
            getFromDbUseCase.insertTodo(todo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                }, {
                    Log.e("error", "Ошибка отправки в БД")
                })
        )
    }

    fun sendNewTodoOnServer(todo: ModelSendNewTodoToServer) {
        disposable.add(
            addNewTodoUseCase.execute(modelSendNewTodoToServer = todo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                }, {
                    Log.e("error", "Ошибка отправки на сервер!")
                })
        )
    }
}