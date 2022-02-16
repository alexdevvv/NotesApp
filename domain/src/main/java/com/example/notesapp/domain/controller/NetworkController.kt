package com.example.notesapp.domain.controller
import com.example.notesapp.domain.model.ModelResponseServer
import com.example.notesapp.domain.model.UserModel
import com.example.notesapp.domain.model.UserDataResponse
import io.reactivex.Single

interface NetworkController {
    fun registration(body: UserModel): Single<ModelResponseServer>

    fun login(body: UserModel): Single<UserDataResponse>
}