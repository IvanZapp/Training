package com.training.app.domain.interactor

import com.training.app.data.repository.PreferencesRepository
import com.training.app.data.repository.UserRepository
import com.training.app.data.repository.network.request.RegisterRequest
import com.training.app.domain.model.SessionModel
import com.training.app.domain.model.UserModel
import com.zappstudio.zappbase.data.exception.ErrorLoginException

class UserInteractor(
    private val prefRepository: PreferencesRepository,
    private val userRepository: UserRepository
) {

    suspend fun login(
        email: String,
        password: String
    ): UserModel {
        val session = userRepository.login(email, password)
        saveSession(session)
        return session.user
    }

    suspend fun refreshToken() {
        prefRepository.getRefreshToken()?.let {
            try {
                val session = userRepository.refreshToken(it)
                saveSession(session)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        } ?: let {
            prefRepository.logout() //Solo preferencias, el WS daría error con un token inválido
            throw ErrorLoginException()
        }
    }

    suspend fun register(
        register: RegisterRequest
    ) {
        val session = userRepository.register(register)
        saveSession(session)
    }

    fun getUser() = prefRepository.getUser()

    private fun saveSession(session: SessionModel) {
        prefRepository.saveUser(session.user)
        prefRepository.setToken(session.token)
        session.refreshToken?.let {
            prefRepository.setRefreshToken(it)
        }
    }

    suspend fun logout() {
        userRepository.logout()
        prefRepository.logout()
    }
}
