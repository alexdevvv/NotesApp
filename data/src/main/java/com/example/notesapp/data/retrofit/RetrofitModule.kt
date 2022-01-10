package com.example.notesapp.data.retrofit

import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.google.gson.GsonBuilder

val retrofitModule = module {
    single {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .readTimeout(40, TimeUnit.SECONDS)
            .connectTimeout(40, TimeUnit.SECONDS)
            .build()

        Retrofit.Builder().baseUrl("https://demo-maven.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    single { get<Retrofit>().create(NotesAPI::class.java) }
}