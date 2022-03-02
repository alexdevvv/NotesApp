package com.example.notesapp.data.controller

import com.example.notesapp.data.database.TodoEntity
import com.example.notesapp.data.database.TodosDao
import com.example.notesapp.domain.controller.DbController
import com.example.notesapp.domain.model.Todo
import io.reactivex.Completable
import io.reactivex.Single

class DbControllerImpl(private val dao: TodosDao) : DbController {
   override fun getTodosForCurrentUser(userId: Long): Single<List<Todo>> {
        return dao.getAllForCurrentUser(userId).map { it.map { todoEntity -> todoEntity.toModel() } }
    }

    override fun deleteTodo(todo: Todo): Completable {
        return dao.deleteTodo(todoEntity = TodoEntity(todo.id, todo.userId,  todo.title, todo.completed))
    }

    override fun insertTodo(todo: Todo): Completable {
        return dao.insertTodo(todoEntity = TodoEntity(todo.id, todo.userId, todo.title, todo.completed))
    }
}