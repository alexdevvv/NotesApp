package com.example.notesapp.domain.usecases

import com.example.notesapp.domain.controller.NetworkController
import com.example.notesapp.domain.model.ModelSendNewTodoToServer

class SendTodosToServerUseCase(private val networkController: NetworkController) {
    fun sendTodosToServer(body: List<ModelSendNewTodoToServer>) =
        networkController.createAllTodos(body)
}