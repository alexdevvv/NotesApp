package com.example.notesapp.domain.usecases

import com.example.notesapp.domain.controller.NetworkController
import com.example.notesapp.domain.model.UserModel
import com.example.notesapp.domain.model.UserDataResponse
import io.reactivex.Single

class LoginUseCase(private val networkController: NetworkController) {
    fun execute(body: UserModel):Single<UserDataResponse> =
        networkController.login(body)

}