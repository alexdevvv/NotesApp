package com.example.notesapp.data.database

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val roomModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            TodosDatabase::class.java, "database1"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single {
        get<TodosDatabase>()
            .todoDataDao
    }
}