package com.example.notesapp.presentation.registration.registration_screen

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notesapp.data.datacontroller.NetworkControllerImpl
import com.example.notesapp.data.retrofit.ModelResponseServer
import com.example.notesapp.data.retrofit.NotesAPI
import com.example.notesapp.data.retrofit.RetrofitKeeper
import com.example.notesapp.domain.controller.NetworkController
import com.example.notesapp.domain.model.ModelSendDataOnServer
import com.example.notesapp.domain.usecases.RegistrationUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class RegistrationScreenVM : ViewModel() {

    var registrationUseCase: RegistrationUseCase? = null
    private val disposable = CompositeDisposable()

    init {
         val retrofit = RetrofitKeeper.getInstance()
         val api: NotesAPI = retrofit!!.create(NotesAPI::class.java)
         val networkController: NetworkController = NetworkControllerImpl(api)
         registrationUseCase = RegistrationUseCase(networkController)
    }

    private val liveDataModel = MutableLiveData<ModelResponseServer>()
    private val liveDataError = MutableLiveData<String>()

    fun getLiveDataModel() = liveDataModel
    fun getLiveDataError() = liveDataError

    fun getResponseServer(modelSendDataOnServer: ModelSendDataOnServer) {
        if (modelSendDataOnServer.username.isNotEmpty() && modelSendDataOnServer.password.isNotEmpty()) {
           disposable.add(registrationUseCase!!
               .execute(modelSendDataOnServer)
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(
                   Consumer {
//                       Log.e("XXX", it.username)
                            getLiveDataModel().postValue(ModelResponseServer(it.id, it.username))
                            },

                   {
                        liveDataModel.postValue(ModelResponseServer(0, ""))
                   }
               ))
        }

    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

}