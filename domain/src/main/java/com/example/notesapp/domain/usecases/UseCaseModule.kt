package com.example.notesapp.domain.usecases

import org.koin.dsl.module

val useCasesModule  = module {
    single<RegistrationUseCase> {
        RegistrationUseCase(get())
    }

    single<LoginUseCase> {
        LoginUseCase(get())
    }

    single<GetDataFromDbUseCase> {
        GetDataFromDbUseCase(get())
    }

    single<AddNewTodoUseCase> {
        AddNewTodoUseCase(get())
    }

    single<GetDataFromServerUseCase> {
        GetDataFromServerUseCase(get())
    }

    single<OverrideDatabaseUseCase> {
        OverrideDatabaseUseCase(get())
    }

    single<SendTodosToServerUseCase> {
        SendTodosToServerUseCase(get())
    }

}