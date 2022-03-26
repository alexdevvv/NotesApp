package com.example.notesapp.data.retrofit

import com.example.notesapp.domain.model.*
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*

interface NotesAPI {
    @POST("users")
    fun getDataRegistrationUser(@Body body: ModelSendUserDataToServer): Single<ModelUserRegistrationResponse>

    @POST("users/login")
    fun getDataLoginUser(@Body body: ModelSendUserDataToServer): Single<ModelUserLoginResponse>

    @POST("todos" )
    fun getDataCreateTodo(@Body body: ModelSendNewTodoToServer, @Query ("userId") userId: Long): Single<ModelGetTodoFromServer>

    @GET("users")
    fun  getUserTodosFromServer(@Query("id") userId: Long): Single<ModelUserLoginResponse>

    @DELETE("todos")
    fun deleteTodoFromServer(@Query("id") todoId: Long): Completable


}

