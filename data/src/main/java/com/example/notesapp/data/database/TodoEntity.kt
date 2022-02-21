package com.example.notesapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.notesapp.data.extension.randomID
import com.example.notesapp.domain.model.Todo

@Entity
class TodoEntity(
    val title: String,
    val completed: Boolean
    )
{
    @PrimaryKey
    var id = (1..1000000).random()
    fun toModel() = Todo(title, completed)
}
