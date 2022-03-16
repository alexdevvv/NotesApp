package com.example.notesapp.data.retrofit

import com.example.notesapp.domain.model.*
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface NotesAPI {
    @POST("users")
    fun getDataRegistrationUser(@Body body: ModelSendUserDataToServer): Single<ModelUserRegistrationResponse>

    @POST("users/login")
    fun getDataLoginUser(@Body body: ModelSendUserDataToServer): Single<ModelUserLoginResponse>

    @POST("todos" )
    fun getDataCreateTodo(@Body body: ModelSendNewTodoToServer, @Query ("userId") userId: Long): Single<ModelGetTodoFromServer>

    @GET("users")
    fun  getUserTodosFromServer(@Query("id") userId: Long): Single<ModelUserLoginResponse>

}

