package com.example.notesapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface TodosDao {

    @Query ("SELECT * FROM TodoEntity")
    fun getAll(): Single<ArrayList<TodoEntity>>

    @Insert
    fun insertTodo(todoEntity: TodoEntity): Completable

    @Delete
    fun deleteTodo(todoEntity: TodoEntity): Completable
}