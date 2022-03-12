package com.example.notesapp.domain.controller
import com.example.notesapp.domain.model.*
import io.reactivex.Single

interface NetworkController {
    fun registration(body: ModelSendUserDataToServer): Single<ModelUserRegistrationResponse>

    fun login(body: ModelSendUserDataToServer): Single<ModelUserLoginResponse>

    fun addNewTodo(body: ModelSendNewTodoToServer): Single<ModelGetTodoFromServer>
}