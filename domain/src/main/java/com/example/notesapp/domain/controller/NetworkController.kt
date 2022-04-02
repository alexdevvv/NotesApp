package com.example.notesapp.domain.controller
import com.example.notesapp.domain.model.*
import io.reactivex.Completable
import io.reactivex.Single

interface NetworkController {
    fun registration(body: ModelSendUserDataToServer): Single<ModelUserRegistrationResponse>

    fun login(body: ModelSendUserDataToServer): Single<ModelUserLoginResponse>

    fun addNewTodo(body: ModelSendNewTodoToServer): Single<ModelGetTodoFromServer>

    fun getTodosFromServer(): Single<List<ModelTodo>>

    fun deleteTodoFromServer(todoId: Long): Completable

    fun createAllTodos(body: List<ModelSendNewTodoToServer>): Single<List<ModelGetTodoFromServer>>


}