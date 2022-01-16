package com.example.notesapp.data.database

import androidx.room.Entity
import com.example.notesapp.domain.model.Todo

@Entity
class TodoEntity(
    val id: Int,
    val title: String,
    val completed: Boolean
    )
{
    fun toModel() = Todo(id, title, completed)
}