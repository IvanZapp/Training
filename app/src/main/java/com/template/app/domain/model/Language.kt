package com.template.app.domain.model

import androidx.annotation.StringRes
import com.template.app.R

enum class Language(
    val key: String,
    val country: String,
    @StringRes val title: Int
) {
    ENGLISH("en", "GB", R.string.english),
    SPANISH("es", "EN", R.string.spanish);

    companion object {
        fun getFromTag(tag: String): Language =
            values().firstOrNull { it.key == tag } ?: ENGLISH
    }
}
