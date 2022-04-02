package com.example.notesapp.data.controller

import com.example.notesapp.data.PreferencesManager
import com.example.notesapp.data.retrofit.NotesAPI
import com.example.notesapp.domain.controller.NetworkController
import com.example.notesapp.domain.model.*
import io.reactivex.Completable
import io.reactivex.Single

class NetworkControllerImpl(
    private val api: NotesAPI,
    private val preferencesManager: PreferencesManager
) : NetworkController {

    private val userId = preferencesManager.getUserIdFromPref()

    override fun registration(body: ModelSendUserDataToServer): Single<ModelUserRegistrationResponse> =
        api.getDataRegistrationUser(body = body)

    override fun login(body: ModelSendUserDataToServer): Single<ModelUserLoginResponse> =
        api.getDataLoginUser(body = body)

    override fun addNewTodo(body: ModelSendNewTodoToServer): Single<ModelGetTodoFromServer> =
        api.getDataCreateTodo(body = body, userId = userId)

    override fun getTodosFromServer(): Single<List<ModelTodo>> =
        api.getUserTodosFromServer(userId = userId).map { it.todos.map { it.toModelTodo2(userId) } }

    override fun deleteTodoFromServer(todoId: Long): Completable =
        api.deleteTodoFromServer(todoId = todoId)

    override fun createAllTodos(body: List<ModelSendNewTodoToServer>): Single<List<ModelGetTodoFromServer>> =
        api.createAllTodos(body, userId)



}