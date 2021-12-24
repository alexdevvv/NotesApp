package com.example.notesapp.domain.controller

import com.example.notesapp.data.retrofit.ModelResponseServer
import com.example.notesapp.domain.model.ModelSendDataOnServer
import io.reactivex.Single

interface NetworkController {
    fun fetchDataUserRegistration(body: ModelSendDataOnServer): Single<ModelResponseServer>
}