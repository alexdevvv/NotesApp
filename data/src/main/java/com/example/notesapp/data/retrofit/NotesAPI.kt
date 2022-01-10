package com.example.notesapp.data.retrofit

import com.example.notesapp.domain.model.ModelResponseServer
import com.example.notesapp.domain.model.ModelSendDataOnServer
import com.example.notesapp.domain.model.UserDataResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface NotesAPI {
    @POST("users")
    fun getDataRegistrationUser(@Body body: ModelSendDataOnServer): Single<ModelResponseServer>

    @POST("users/login")
    fun getDataLoginUser(@Body body: ModelSendDataOnServer): Single<UserDataResponse>

}