package com.example.notesapp.domain.usecases

import com.example.notesapp.domain.controller.NetworkController
import com.example.notesapp.domain.model.ModelSendUserDataToServer

class RegistrationUseCase(private val networkController: NetworkController) {
    fun execute(body: ModelSendUserDataToServer) =
        networkController.registration(body = body)

}