package com.training.app.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    val id: String,
    val name: String,
    val lastName: String,
    var email: String,
    var phoneNumber: String
) : Parcelable
