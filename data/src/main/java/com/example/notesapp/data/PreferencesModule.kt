package com.example.notesapp.data

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val preferencesModule = module {
    single<PreferencesManager> {
        PreferencesManager(context = androidContext())
    }
}