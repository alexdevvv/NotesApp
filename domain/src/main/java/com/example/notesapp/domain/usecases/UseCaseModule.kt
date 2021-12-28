package com.example.notesapp.presentation.registration.registration_screen

import com.example.notesapp.domain.usecases.RegistrationUseCase
import org.koin.dsl.module

val useCasesModule  = module {
    single<RegistrationUseCase> {
        RegistrationUseCase(get())
    }

}