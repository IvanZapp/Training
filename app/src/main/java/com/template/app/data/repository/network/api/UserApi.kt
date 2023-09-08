/*
 *
 *  * Copyright (c) 2022.
 *  * All rights reserved to Zapp Studio S.L
 *  * Av. de Zarandona, 29, 1 planta, 30007 Murcia
 *
 */

package com.template.app.data.repository.network.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.template.app.data.entity.SessionEntity
import com.template.app.data.entity.UserEntity
import com.template.app.data.repository.network.request.LoginRequest
import com.template.app.data.repository.network.request.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {

    @POST(ENDPOINT_REFRESH_TOKEN)
    suspend fun refreshToken(@Body request: RequestRefreshToken): Response<SessionEntity>
    @JsonClass(generateAdapter = true)
    data class RequestRefreshToken(
        @Json(name = "refreshToken")
        val refreshToken: String,
        @Json(name = "push")
        val push: String?
    )
    @POST(ENDPOINT_LOGIN)
    suspend fun login(@Body request: LoginRequest): Response<SessionEntity>

    @POST(ENDPOINT_LOGOUT)
    suspend fun logout(): Response<Any>

    @POST(ENDPOINT_REGISTER)
    suspend fun register(@Body request: RegisterRequest): Response<SessionEntity>

    @POST(ENDPOINT_RECOVER_PASSWORD)
    suspend fun recoverPassword(@Body request: RequestRecoverPassword): Response<Void>

    @GET(ENDPOINT_GET_USER)
    suspend fun userLogged(): Response<UserEntity>

    data class RequestUpdatePassword(
        @Json(name = "oldPassword")
        val oldPassword: String,
        @Json(name = "newPassword")
        val newPassword: String
    )

    data class RequestUpdateUser(
        @Json(name = "name")
        val name: String,
        @Json(name = "lastName")
        val lastName: String,
        @Json(name = "phone")
        val phone: String,
        @Json(name = "birthday")
        val birthday: String
    )

    data class RequestRecoverPassword(
        @Json(name = "email")
        val email: String
    )

    companion object {

        private const val ENDPOINT_LOGIN =
            "api/user/login"

        private const val ENDPOINT_GET_USER =
            "api/user"

        private const val ENDPOINT_LOGOUT =
            "api/user/logout"

        private const val ENDPOINT_REGISTER =
            "api/user/register"

        private const val ENDPOINT_RECOVER_PASSWORD =
            "api/user/recoverPassword"

        private const val ENDPOINT_REFRESH_TOKEN =
            "api/user/refreshToken"
    }
}
