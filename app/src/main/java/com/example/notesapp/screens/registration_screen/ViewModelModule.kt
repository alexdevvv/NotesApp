package com.example.notesapp.screens.registration_screen

import com.example.notesapp.screens.login_screen.LoginScreenVM
import com.example.notesapp.screens.notes_screen.NotesScreenVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<NotesScreenVM>()
    viewModel<RegistrationScreenVM>()
    viewModel<LoginScreenVM>()

}