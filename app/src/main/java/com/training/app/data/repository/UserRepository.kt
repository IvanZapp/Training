/*
 *
 *  * Copyright (c) 2022.
 *  * All rights reserved to Zapp Studio S.L
 *  * Av. de Zarandona, 29, 1 planta, 30007 Murcia
 *
 */

package com.training.app.data.repository

import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import com.training.app.data.mapper.SessionMapper
import com.training.app.data.mapper.UserMapper
import com.training.app.data.repository.network.api.UserApi
import com.training.app.data.repository.network.api.UserApi.RequestRecoverPassword
import com.training.app.data.repository.network.api.UserApi.RequestRefreshToken
import com.training.app.data.repository.network.request.LoginRequest
import com.training.app.data.repository.network.request.RegisterRequest
import com.training.app.domain.model.SessionModel
import com.training.app.domain.model.UserModel
import com.zappstudio.webservice.BaseRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class UserRepository(
    private val baseRequest: BaseRequest,
    private val userApi: UserApi,
    private val userMapper: UserMapper,
    private val sessionMapper: SessionMapper
) {

    suspend fun login(
        email: String,
        password: String
    ): SessionModel =
        baseRequest.request {
            val pushToken = getPushToken()
            userApi.login(LoginRequest(email, password, pushToken ?: ""))
        }.let { sessionMapper.toModel(it) }

    suspend fun refreshToken(
        refreshToken: String
    ): SessionModel =
        baseRequest.request {
            userApi.refreshToken(RequestRefreshToken(refreshToken, getPushToken()))
        }.let { sessionMapper.toModel(it) }

    suspend fun register(
        registerRequest: RegisterRequest
    ): SessionModel =
        baseRequest.request {
            userApi.register(registerRequest)
        }.let { sessionMapper.toModel(it) }

    suspend fun getUserLogged(): UserModel =
        baseRequest.request {
            userApi.userLogged()
        }.let {
            userMapper.toModel(it)
        }

    suspend fun logout() =
        baseRequest.request {
            userApi.logout()
        }

    suspend fun recoverPassword(email: String) =
        baseRequest.request {
            userApi.recoverPassword(RequestRecoverPassword(email))
        }

    suspend fun getPushToken(): String? {
        return withContext(Dispatchers.Main) {
            Firebase.messaging.token.await()
        }
    }
}
