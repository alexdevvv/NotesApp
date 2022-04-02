package com.example.notesapp.domain.model

private const val ID_FOR_SERVER = -1L
data class ModelTodo(
    val userId: Long,
    val title: String,
    val completed: Boolean
){

    var dbId = (1..1000000).random()
    var idTodoFromServer = ID_FOR_SERVER

    constructor(dbId: Int, userId: Long, title: String, completed: Boolean) : this(userId, title, completed){
        this.dbId = dbId
    }

    constructor(idTodoFromServer: Long, userId: Long, title: String, completed: Boolean): this(userId, title, completed){
        this.idTodoFromServer = idTodoFromServer
    }

}

