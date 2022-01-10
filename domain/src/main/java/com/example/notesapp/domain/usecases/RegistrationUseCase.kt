package com.example.notesapp.domain.usecases

import com.example.notesapp.domain.controller.NetworkController
import com.example.notesapp.domain.model.UserModel

class RegistrationUseCase(private val networkController: NetworkController) {
    fun execute(body: UserModel) =
        networkController.registration(body = body)

}