package com.template.app.app.ui.splash

import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.template.app.app.ui.base.BaseViewModel
import com.template.app.domain.interactor.UserInteractor
import com.zappstudio.zappbase.app.ext.toResultEventLiveData
import com.zappstudio.zappbase.app.ext.toResultLiveData
import com.zappstudio.zappbase.data.exception.UnsupportedVersionException
import com.zappstudio.zappbase.domain.model.ResultEvent
import com.zappstudio.zappbase.gms.data.repository.remoteconfig.RemoteConfigRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch

class SplashViewModel(
    private val userInteractor: UserInteractor,
    private val remoteConfigRepository: RemoteConfigRepository
) : BaseViewModel() {

    val isSetupFinished =
        liveData {
            var result = ResultEvent.onSuccess()
            listOf(
                viewModelScope.launch(Dispatchers.IO) {
                    delay(SPLASH_TIME_SPLASH_SHOWING * 1000)
                },
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        if (remoteConfigRepository.isAppVersionOutDate())
                            throw UnsupportedVersionException(remoteConfigRepository.getVersionInfo())
                    } catch (e: Exception) {
                        result = ResultEvent.onError(e)
                    }
                },
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        userInteractor.getUser()
                    } catch (e: Exception) {
                        result = ResultEvent.onError(e)
                    }
                }
            ).joinAll()
            emit(result)
        }

    fun logout() =
        toResultEventLiveData {
            userInteractor.logout()
        }

    companion object {
        const val SPLASH_TIME_SPLASH_SHOWING = 3L
    }
}
