package com.example.notesapp.domain.usecases

import org.koin.dsl.module

val useCasesModule  = module {
    single<RegistrationUseCase> {
        RegistrationUseCase(get())
    }

    single<LoginUseCase> {
        LoginUseCase(get())
    }

    single<GetFromDbUseCase> {
        GetFromDbUseCase(get())
    }

    single<AddNewTodoUseCase> {
        AddNewTodoUseCase(get())
    }

}