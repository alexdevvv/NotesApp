package com.example.notesapp.domain.model

class ModelTodoFromServer(val id: Long, val title: String, val completed: Boolean) {

    fun toModelTodo(): ModelTodo = ModelTodo(userId = id, title = title, completed = completed)
}