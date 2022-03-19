package com.example.notesapp.domain.controller

import com.example.notesapp.domain.model.ModelTodo
import io.reactivex.Completable
import io.reactivex.Single

interface DbController {
    fun getTodosForCurrentUser(userId: Long): Single<List<ModelTodo>>

    fun deleteTodo(modelTodo: ModelTodo): Completable

    fun insertTodo(modelTodo: ModelTodo): Completable
}