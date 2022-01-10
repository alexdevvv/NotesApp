package com.example.notesapp.screens.login_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notesapp.domain.model.UserModel
import com.example.notesapp.domain.model.UserDataResponse
import com.example.notesapp.domain.usecases.LoginUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class LoginScreenVM(var loginUseCase: LoginUseCase): ViewModel() {

    private val disposable = CompositeDisposable()
    private val liveDataModel = MutableLiveData<UserDataResponse>()
    private val liveDataError = MutableLiveData<String>()
    private val liveDataUserDataEmpty = MutableLiveData<String>()

    fun getLiveDataError(): LiveData<String> = liveDataError
    fun getLiveDataModel(): LiveData<UserDataResponse> = liveDataModel
    fun getLiveDataUserDataEmpty(): LiveData<String> = liveDataUserDataEmpty

    fun login(userModel: UserModel){
        if (userModel.username.isNotEmpty() && userModel.password.isNotEmpty()) {
            disposable.add(
                loginUseCase
                    .execute(body = userModel)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        Consumer {
                            liveDataModel.postValue(it)
                        }, {
                            if (it is HttpException && it.response()?.code() == 400) {
                                liveDataError.postValue("Пользователь не найден")
                            } else {
                                liveDataError.postValue(it.toString())
                            }
                        }
                    )
            )
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