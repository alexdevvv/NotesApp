package com.example.notesapp.domain.controller

import com.example.notesapp.domain.model.Todo
import io.reactivex.Completable
import io.reactivex.Single

interface DbController {
    fun getTodosForCurrentUser(userId: Long): Single<List<Todo>>

    fun deleteTodo(todo: Todo): Completable

    fun insertTodo(todo: Todo): Completable
}