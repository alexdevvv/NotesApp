package com.example.notesapp.domain.model

class ModelUserLoginResponse(var id:Long,
                             var username: String,
                             val todos: List<Todo>)
