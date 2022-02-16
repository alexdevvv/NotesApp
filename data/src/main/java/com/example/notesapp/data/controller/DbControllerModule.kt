package com.example.notesapp.data.controller

import com.example.notesapp.domain.controller.DbController
import com.example.notesapp.domain.controller.NetworkController
import org.koin.dsl.module

val dbControllerModule = module {
    single<DbController>{
        DbControllerImpl(get())
    }
}


