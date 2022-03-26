package com.example.notesapp.domain.usecases

import com.example.notesapp.domain.controller.DbController
import com.example.notesapp.domain.model.ModelTodo
import io.reactivex.Completable
import io.reactivex.Single

class GetDataFromDbUseCase(private val dbController: DbController) {

    fun getTodosFromDbForCurrentUser(userId: Long): Single<List<ModelTodo>> {
        return dbController.getTodosForCurrentUser(userId = userId)
    }

    fun deleteTodo(modelTodo: ModelTodo): Completable {
        return dbController.deleteTodo(modelTodo)
    }

    fun insertTodo(modelTodo: ModelTodo): Completable {
        return dbController.insertTodo(modelTodo)
    }

}