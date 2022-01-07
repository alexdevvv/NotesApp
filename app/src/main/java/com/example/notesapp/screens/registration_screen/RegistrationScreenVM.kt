package com.example.notesapp.screens.registration_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notesapp.domain.model.ModelResponseServer
import com.example.notesapp.domain.model.ModelSendDataOnServer
import com.example.notesapp.domain.usecases.RegistrationUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class RegistrationScreenVM(private val registrationUseCase: RegistrationUseCase) : ViewModel() {

    private val disposable = CompositeDisposable()
    private val liveDataModel = MutableLiveData<ModelResponseServer>()
    private val liveDataError = MutableLiveData<String>()
    private val liveDataUserDataEmpty = MutableLiveData<String>()

    fun getLiveDatError(): LiveData<String> = liveDataError
    fun getLiveDataModel(): LiveData<ModelResponseServer>  = liveDataModel
    fun getLiveDataUserDataEmpty(): LiveData<String> = liveDataUserDataEmpty

    fun getResponseServer(modelSendDataOnServer: ModelSendDataOnServer) {
        if (modelSendDataOnServer.username.isNotEmpty() && modelSendDataOnServer.password.isNotEmpty()) {
           disposable.add(registrationUseCase!!
               .execute(modelSendDataOnServer)
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(
                   Consumer {
                            liveDataModel.postValue(ModelResponseServer(it.id, it.username))
                            },
                   {
                       if (it is HttpException && it.response()?.code() == 400) {
                           liveDataError.postValue("Пользователь с таким именем уже существует.")
                       } else {
                           liveDataError.postValue(it.toString())
                       }
                   }
               ))
        } else {
            liveDataUserDataEmpty.postValue("Не все поля заполнены!")
        }
    }


    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
        disposable.clear()
    }

}