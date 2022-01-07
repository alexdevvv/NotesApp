package com.example.notesapp.data.controller

import com.example.notesapp.data.retrofit.NotesAPI
import com.example.notesapp.domain.controller.NetworkController
import com.example.notesapp.domain.model.ModelSendDataOnServer
import io.reactivex.Single

class NetworkControllerImpl(private val api: NotesAPI): NetworkController {

    override fun fetchDataUserRegistration(body: ModelSendDataOnServer): Single<com.example.notesapp.domain.model.ModelResponseServer> {
       return api.getDataRegistrationUser(body = body)

    }
}