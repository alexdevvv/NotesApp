package com.example.notesapp.domain.model

class Todo(
    val title: String,
    val completed: Boolean
){
    var id = (1..1000000).random()

    constructor(id: Int, title: String, completed: Boolean) : this(title, completed){
        this.id = id
    }

}

