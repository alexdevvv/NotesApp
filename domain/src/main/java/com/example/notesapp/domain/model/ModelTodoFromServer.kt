package com.example.notesapp.domain.model

import android.icu.text.CaseMap

class ModelTodoFromServer(val id: Long, val title: String, val completed: Boolean) {

    fun toModelTodo(): ModelTodo = ModelTodo(id = id, title = title, completed = completed)
}