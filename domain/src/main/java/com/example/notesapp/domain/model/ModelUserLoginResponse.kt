package com.example.notesapp.domain.model

data class ModelUserLoginResponse(var id:Long,
                             var username: String,
                             val todos: MutableList<Todo>)
