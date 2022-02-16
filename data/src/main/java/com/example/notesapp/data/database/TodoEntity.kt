package com.example.notesapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.notesapp.domain.model.Todo

@Entity
class TodoEntity(
    val title: String,
    val completed: Boolean
    )

{
    @PrimaryKey(autoGenerate = true)
    var id: Int = -1

    fun toModel() = Todo(title, completed)
}