package com.example.notesapp.domain.usecases

import com.example.notesapp.domain.controller.DbController
import com.example.notesapp.domain.model.Todo
import io.reactivex.Completable
import io.reactivex.Single

class GetFromDbUseCase(private val dbController: DbController) {

    fun getTodosFromDb(): Single<List<Todo>> {
        return dbController.getTodos()
    }

    fun deleteTodo(todo: Todo): Completable {
        return dbController.deleteTodo(todo)
    }

    fun insertTodo(todo: Todo): Completable {
        return dbController.insertTodo(todo)
    }

}