package com.example.notesapp.presentation.registration.di

import com.example.notesapp.data.datacontroller.NetworkControllerImpl
import com.example.notesapp.domain.controller.NetworkController
import org.koin.dsl.module

val networkControllerModule = module {
    single<NetworkController>{
        NetworkControllerImpl(get())
    }
}