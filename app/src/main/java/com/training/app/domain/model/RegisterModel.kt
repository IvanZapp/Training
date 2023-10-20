
package com.training.app.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RegisterModel(
    val name: String,
    val lastName: String,
    var email: String,
    var phoneNumber: String,
    var password: String,
) : Parcelable
