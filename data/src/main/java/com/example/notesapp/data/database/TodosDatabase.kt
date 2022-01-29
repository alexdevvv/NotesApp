package com.example.notesapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TodoEntity::class], version = 3)
 abstract class TodosDatabase: RoomDatabase() {
    abstract val todoDataDao: TodosDao
}