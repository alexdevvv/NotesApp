package com.example.notesapp.data.database

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface TodosDao {

    @Query("SELECT * FROM TodoEntity WHERE userId = :userId")
    fun getAllForCurrentUser(userId: Long): Single<List<TodoEntity>>

    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun insertTodo(todoEntity: TodoEntity): Completable

    @Delete
    fun deleteTodo(todoEntity: TodoEntity): Completable

    @Transaction
    fun overrideTodosTable(todos: List<TodoEntity>) {
        clearDb()
        insertAllTodos(todos)
    }

    @Query("DELETE  FROM TodoEntity")
    fun clearDb()


    @Insert
    fun insertAllTodos(todos: List<TodoEntity>)


}