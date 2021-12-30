package com.example.notesapp

import android.app.Application
import com.example.notesapp.data.retrofit.retrofitModule
import com.example.notesapp.data.datacontroller.networkControllerModule
import com.example.notesapp.domain.usecases.useCasesModule
import com.example.notesapp.presentation.registration.registration_screen.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(retrofitModule, viewModelModule, networkControllerModule, useCasesModule))
        }
    }
}