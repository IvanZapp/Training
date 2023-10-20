package com.training.app.domain.model

data class SessionModel(
    val token: String,
    var refreshToken: String?,
    var user: UserModel
)
