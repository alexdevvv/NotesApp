package com.example.notesapp.data.retrofit

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {
    single { Retrofit.Builder().baseUrl("https://demo-maven.herokuapp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build() }

    single { get<Retrofit>().create(NotesAPI::class.java) }
}