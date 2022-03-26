package com.example.notesapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TodoEntity::class], version = 6, exportSchema = false)
 abstract class TodosDatabase: RoomDatabase() {
    abstract val todoDataDao: TodosDao
}