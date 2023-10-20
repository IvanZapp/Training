/*
 *
 *  * Copyright (c) 2022.
 *  * All rights reserved to Zapp Studio S.L
 *  * Av. de Zarandona, 29, 1 planta, 30007 Murcia
 *
 */

package com.training.app.data.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.training.app.data.mapper.UserMapper
import com.training.app.domain.model.Language
import com.training.app.domain.model.UserModel
import com.zappstudio.zappbase.app.ext.observeKey
import com.zappstudio.zappbase.data.exception.ErrorLoginException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*

@Suppress("unused")
class PreferencesRepository(
    private val unencryptedPreferences: SharedPreferences,
    private val encryptedPreferences: SharedPreferences,
    private val userMapper: UserMapper,
    private val context: Context
) {

    /**
     * By default return English
     * Change language is not implemented at the moment, because in aab languages we can get problems
     * @return
     */
    fun getLocale(): Language =
        Language.getFromTag(unencryptedPreferences.getString(PREF_KEY_LOCALE_SELECTED, null) ?: "")

    fun setLocale(language: Language) = unencryptedPreferences.edit {
        putString(PREF_KEY_LOCALE_SELECTED, language.key)
    }

    fun getToken(): String? =
        encryptedPreferences.getString(PREF_KEY_TOKEN, null)

    fun setToken(token: String) =
        encryptedPreferences.edit {
            putString(PREF_KEY_TOKEN, token)
        }

    fun getRefreshToken(): String? =
        encryptedPreferences.getString(PREF_KEY_REFRESH_TOKEN, null)

    fun setRefreshToken(token: String) =
        encryptedPreferences.edit {
            putString(PREF_KEY_REFRESH_TOKEN, token)
        }.apply { }

    fun getUser(): UserModel =
        unencryptedPreferences.getString(PREF_KEY_USER, null)?.let { userMapper.toModel(it) } ?: throw ErrorLoginException()

    @OptIn(ExperimentalCoroutinesApi::class)
    fun observeUser(): Flow<UserModel?> = unencryptedPreferences.observeKey(PREF_KEY_USER, "")
        .map {
            try {
                getUser()
            } catch (e: Exception) {
                null
            }
        }

    fun saveUser(
        user: UserModel
    ) = unencryptedPreferences.edit {
        putString(PREF_KEY_USER, userMapper.modelToJson(user).toString())
    }

    fun setPushToken(
        tokenPush: String
    ) =
        unencryptedPreferences.edit {
            putString(PREF_PUSH_TOKEN, tokenPush)
        }

    fun logout() {
        encryptedPreferences.edit {
            remove(PREF_KEY_USER)
            remove(PREF_KEY_TOKEN)
            remove(PREF_KEY_REFRESH_TOKEN)
        }
        unencryptedPreferences.edit {
            remove(PREF_KEY_USER)
            remove(PREF_PUSH_TOKEN)
        }
    }

    companion object {
        // CAUTION!! Preferences name be careful when change this, because it will change the preferences files name.
        const val PREF_KEY_USER = "PREF_KEY_USER"
        const val PREF_KEY_TOKEN = "PREF_KEY_TOKEN"
        const val PREF_KEY_REFRESH_TOKEN = "PREF_KEY_REFRESH_TOKEN"
        const val PREF_PUSH_TOKEN = "PREF_PUSH_TOKEN"
        const val PREF_KEY_LOCALE_SELECTED = "PREF_KEY_LOCALE_SELECTED"
    }
}
