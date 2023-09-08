package com.template.app.app.ui.register

import androidx.lifecycle.MutableLiveData
import com.template.app.app.ui.base.BaseViewModel
import com.template.app.data.repository.network.request.RegisterRequest
import com.template.app.domain.interactor.UserInteractor
import com.template.app.domain.model.RegisterModel
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
