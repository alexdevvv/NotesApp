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

    override fun registration(body: ModelSendUserDataToServer): Single<ModelUserRegistrationResponse> =
        api.getDataRegistrationUser(body = body)

    override fun login(body: ModelSendUserDataToServer): Single<ModelUserLoginResponse> =
        api.getDataLoginUser(body = body)

    override fun addNewTodo(body: ModelSendNewTodoToServer): Single<ModelGetTodoFromServer> =
        api.getDataCreateTodo(body = body, userId = preferencesManager.getUserIdFromPref())

    override fun getTodosFromServer(): Single<List<ModelTodo>> =
        api.getUserTodosFromServer(userId = preferencesManager.getUserIdFromPref()).map { it.todos.map { it.toModelTodo() } }

    override fun deleteTodoFromServer(todoId: Long): Completable =
        api.deleteTodoFromServer(todoId = todoId)

}