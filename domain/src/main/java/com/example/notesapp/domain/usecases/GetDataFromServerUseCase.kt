package com.example.notesapp.domain.usecases

import com.example.notesapp.domain.controller.NetworkController
import com.example.notesapp.domain.model.ModelTodo
import com.example.notesapp.domain.model.ModelUserLoginResponse
import io.reactivex.Completable
import io.reactivex.Single

class GetDataFromServerUseCase(private val networkController: NetworkController) {

    fun getTodosFromServer(): Single<List<ModelTodo>> =
        networkController.getTodosFromServer()

    fun deleteTodo(todoId: Long): Completable = networkController.deleteTodoFromServer(todoId)

}