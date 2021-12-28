package com.example.notesapp.presentation.registration.registration_screen

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<RegistrationScreenVM>()
}