package com.training.app.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SessionEntity(
    @Json(name = "token")
    val token: String,
    @Json(name = "refresh_token")
    var refreshToken: String?,
    @Json(name = "user")
    var user: UserEntity
)