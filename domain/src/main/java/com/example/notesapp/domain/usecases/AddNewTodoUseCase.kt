package com.example.notesapp.domain.usecases

import com.example.notesapp.domain.controller.NetworkController
import com.example.notesapp.domain.model.ModelGetTodoFromServer
import com.example.notesapp.domain.model.ModelSendNewTodoToServer
import io.reactivex.Single

class AddNewTodoUseCase(private val networkController: NetworkController) {
    fun execute(modelSendNewTodoToServer: ModelSendNewTodoToServer): Single<ModelGetTodoFromServer> =
         networkController.addNewTodo(body = modelSendNewTodoToServer)



}