package com.example.notesapp.domain.usecases

import com.example.notesapp.domain.controller.NetworkController
import com.example.notesapp.domain.model.ModelSendDataOnServer
import com.example.notesapp.domain.model.UserDataResponse
import io.reactivex.Single

class LoginUseCase(val networkController: NetworkController) {
    fun execute(body: ModelSendDataOnServer):Single<UserDataResponse> {
        return networkController.fetchDataUserLogin(body)
    }
}