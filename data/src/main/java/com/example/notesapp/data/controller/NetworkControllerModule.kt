package com.example.notesapp.data.controller

import com.example.notesapp.domain.controller.NetworkController
import org.koin.dsl.module

val networkControllerModule = module {
    single<NetworkController>{
        NetworkControllerImpl(get())
    }
}

