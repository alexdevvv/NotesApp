package com.example.notesapp.domain.usecases

import com.example.notesapp.domain.controller.DbController
import com.example.notesapp.domain.model.ModelSendNewTodoToServer
import com.example.notesapp.domain.model.ModelTodo

class OverrideDatabaseUseCase(private val dbController: DbController) {
    fun overrideTodosTable(list: List<ModelTodo>) =
        dbController.overrideTodosTable(list)
}