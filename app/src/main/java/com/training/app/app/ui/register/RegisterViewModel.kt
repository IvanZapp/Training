package com.training.app.app.ui.register

import androidx.lifecycle.MutableLiveData
import com.training.app.app.ui.base.BaseViewModel
import com.training.app.data.repository.network.request.RegisterRequest
import com.training.app.domain.interactor.UserInteractor
import com.training.app.domain.model.RegisterModel
import com.zappstudio.zappbase.app.ext.toResultEventLiveData

class RegisterViewModel(
    private val userInteractor: UserInteractor
) : BaseViewModel() {

    val user: MutableLiveData<RegisterModel> = MutableLiveData()

    // TODO
    fun register() = toResultEventLiveData {
        userInteractor.register(
            RegisterRequest(
                "email",
                "password",
                "name",
                "lastName",
                "pushToken"
            )
        )
    }
}
