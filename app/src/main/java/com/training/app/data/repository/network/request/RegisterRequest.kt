package com.training.app.data.repository.network.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.training.app.domain.model.UserModel

@JsonClass(generateAdapter = true)
data class RegisterRequest(
    @Json(name = "email")
    val email: String,
    @Json(name = "password")
    val password: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "lastName")
    val lastName: String,
    @Json(name = "pushToken")
    val pushToken: String
) {
    constructor(
        userModel: UserModel,
        password: String,
        pushToken: String
    ) : this(
        userModel.email,
        password,
        userModel.name,
        userModel.lastName,
        pushToken
    )
}
