package com.example.notesapp.domain.usecases

import com.example.notesapp.domain.controller.NetworkController
import com.example.notesapp.domain.model.ModelSendUserDataToServer
import com.example.notesapp.domain.model.ModelUserLoginResponse
import io.reactivex.Single

class LoginUseCase(private val networkController: NetworkController) {
    fun execute(body: ModelSendUserDataToServer):Single<ModelUserLoginResponse> =
        networkController.login(body)

}