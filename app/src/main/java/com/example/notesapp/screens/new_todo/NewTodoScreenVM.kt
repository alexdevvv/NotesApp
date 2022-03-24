package com.example.notesapp.screens.new_todo

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.notesapp.domain.model.ModelSendNewTodoToServer
import com.example.notesapp.domain.model.ModelTodo
import com.example.notesapp.domain.usecases.AddNewTodoUseCase
import com.example.notesapp.domain.usecases.GetDataFromDbUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NewTodoScreenVM(
    private val getDataFromDbUseCase: GetDataFromDbUseCase,
    private val addNewTodoUseCase: AddNewTodoUseCase
) : ViewModel() {

    private val disposable = CompositeDisposable()

    fun insertTodoInDatabase(modelTodo: ModelTodo) {
        disposable.add(
            getDataFromDbUseCase.insertTodo(modelTodo)
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
                           // мне нужно получать id заметки и передавать его при создании todo
                }, {
                    Log.e("error", "Ошибка отправки на сервер!")
                })
        )
    }
}