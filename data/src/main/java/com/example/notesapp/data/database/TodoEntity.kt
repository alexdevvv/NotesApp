package com.example.notesapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.notesapp.domain.model.ModelTodo

@Entity
class TodoEntity(
    @PrimaryKey
    val id: Int,
    val userId: Long,
    val title: String,
    val completed: Boolean
) {

    fun toModel() = ModelTodo(dbId = id, userId = userId, title = title, completed = completed)

    companion object{
        fun fromModel(model: ModelTodo) = TodoEntity(model.dbId, model.userId, model.title, model.completed)

    }

}
