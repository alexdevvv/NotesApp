package com.example.notesapp.domain.usecases

import com.example.notesapp.domain.controller.NetworkController
import com.example.notesapp.domain.model.ModelUserLoginResponse
import io.reactivex.Single

class GetDataFromServerUseCase(private val networkController: NetworkController) {
    fun execute(): Single<ModelUserLoginResponse> =
        networkController.getTodosFromServer()

}