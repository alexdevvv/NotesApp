package com.example.notesapp.domain.model

class Todo(
    val userId: Long,
    val title: String,
    val completed: Boolean
){

    var id = (1..1000000).random()

    constructor(id: Int, userId: Long, title: String, completed: Boolean) : this(userId, title, completed){
        this.id = id
    }

}

