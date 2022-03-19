package com.example.notesapp.data.controller

import com.example.notesapp.data.database.TodoEntity
import com.example.notesapp.data.database.TodosDao
import com.example.notesapp.domain.controller.DbController
import com.example.notesapp.domain.model.ModelTodo
import io.reactivex.Completable
import io.reactivex.Single

class DbControllerImpl(private val dao: TodosDao) : DbController {
   override fun getTodosForCurrentUser(userId: Long): Single<List<ModelTodo>> {
        return dao.getAllForCurrentUser(userId).map { it.map { todoEntity -> todoEntity.toModel() } }
    }

    override fun deleteTodo(modelTodo: ModelTodo): Completable {
        return dao.deleteTodo(todoEntity = TodoEntity(modelTodo.dbId, modelTodo.id,  modelTodo.title, modelTodo.completed))
    }

    override fun insertTodo(modelTodo: ModelTodo): Completable {
        return dao.insertTodo(todoEntity = TodoEntity(modelTodo.dbId, modelTodo.id, modelTodo.title, modelTodo.completed))
    }
}