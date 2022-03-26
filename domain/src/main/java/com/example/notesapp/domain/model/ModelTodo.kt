package com.example.notesapp.domain.model

data class ModelTodo(
    val userId: Long,
    val title: String,
    val completed: Boolean
){

    var dbId = (1..1000000).random()

    constructor(dbId: Int, userId: Long, title: String, completed: Boolean) : this(userId, title, completed){
        this.dbId = dbId
    }

}

