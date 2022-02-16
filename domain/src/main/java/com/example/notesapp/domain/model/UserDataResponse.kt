package com.example.notesapp.domain.model

class UserDataResponse(var id:Long,
                       var username: String,
                       val todos: List<Todo>)
