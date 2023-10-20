package com.training.app.app.ui.main

import com.training.app.app.ui.base.BaseViewModel
import com.zappstudio.zappbase.app.ext.toResultLiveData
import com.zappstudio.zappbase.data.exception.PrivacyPolicyMustBeAccepted
import com.zappstudio.zappbase.gms.data.repository.remoteconfig.RemoteConfigRepository

class MainViewModel(
    private val remoteConfigRepository: RemoteConfigRepository
) : BaseViewModel() {

    val isPrivacyPolicyAccepted = toResultLiveData {
        if (remoteConfigRepository.haveToAcceptPrivacyPolicy("2")) {
            remoteConfigRepository.getPrivacyVersion()?.let {
                throw PrivacyPolicyMustBeAccepted(it)
            }
        }
        true
    }
}