package com.example.notesapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.notesapp.domain.model.Todo

@Entity
class TodoEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val completed: Boolean
) {

    fun toModel() = Todo(id = id, title = title, completed = completed)
}
