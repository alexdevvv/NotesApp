package com.example.notesapp

import android.app.Application
import com.example.notesapp.data.controller.dbControllerModule
import com.example.notesapp.data.retrofit.retrofitModule
import com.example.notesapp.data.controller.networkControllerModule
import com.example.notesapp.data.database.roomModule
import com.example.notesapp.data.preferencesModule
import com.example.notesapp.domain.usecases.useCasesModule
import com.example.notesapp.screens.registration_screen.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(retrofitModule, viewModelModule, networkControllerModule, useCasesModule, roomModule, dbControllerModule, preferencesModule))
        }
    }
}