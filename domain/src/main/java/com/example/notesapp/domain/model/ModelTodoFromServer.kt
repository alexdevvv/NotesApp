package com.example.notesapp.domain.model

class ModelTodoFromServer(val id: Long, val title: String, val completed: Boolean) {

    fun toModelTodo(userId: Long): ModelTodo = ModelTodo(userId = userId, title = title, completed = completed)
}