package com.example.notesapp.domain.usecases

import com.example.notesapp.data.retrofit.ModelResponseServer
import com.example.notesapp.domain.controller.NetworkController
import com.example.notesapp.domain.model.ModelSendDataOnServer
import io.reactivex.Single

class RegistrationUseCase(val networkController: NetworkController) {
    fun execute(body: ModelSendDataOnServer): Single<ModelResponseServer> {
       return networkController.fetchDataUserRegistration(body = body)
    }

}