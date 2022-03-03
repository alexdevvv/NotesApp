package com.example.notesapp.data.controller

import com.example.notesapp.data.retrofit.NotesAPI
import com.example.notesapp.domain.controller.NetworkController
import com.example.notesapp.domain.model.ModelResponseServer
import com.example.notesapp.domain.model.UserDataResponse
import com.example.notesapp.domain.model.UserModel
import io.reactivex.Single

class NetworkControllerImpl(private val api: NotesAPI) : NetworkController {

    override fun registration(body: UserModel): Single<ModelResponseServer> =
        api.getDataRegistrationUser(body = body)

    override fun login(body: UserModel): Single<UserDataResponse> =
        api.getDataLoginUser(body = body)

}