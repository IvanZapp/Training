package com.template.app.app.ui.login

import androidx.lifecycle.MutableLiveData
import com.template.app.app.ui.base.BaseViewModel
import com.template.app.domain.interactor.UserInteractor
import com.template.app.domain.model.UserModel
import com.zappstudio.zappbase.app.ext.toResultLiveData

class LoginViewModel(
    private val userInteractor: UserInteractor
) : BaseViewModel() {

    val email: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()

    val user = MutableLiveData<UserModel>()

    init {
        if (com.training.app.BuildConfig.DEBUG) {
            email.value = "active@zapp-studio.com"
            password.value = "holahola"
        }
    }

    fun login() = toResultLiveData {
        userInteractor.login(email.value ?: "", password.value ?: "").apply {
            user.postValue(this)
        }
    }
}
