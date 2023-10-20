package com.training.app.data.repository.network

import com.training.app.BuildConfig
import com.training.app.data.repository.PreferencesRepository
import com.training.app.domain.interactor.UserInteractor
import com.zappstudio.webservice.model.ConfigurationRestClient
import com.zappstudio.zappbase.data.exception.ErrorLoginException
import com.zappstudio.zappbase.data.mapper.serializer.time.parseDate
import org.koin.core.component.inject
import java.time.LocalDateTime
import java.util.*

class ConfigurationRestClientImpl(
    private val preferencesRepository: PreferencesRepository
) : ConfigurationRestClient {

    private val userInteractor: UserInteractor by inject()

    override val basepath: String = BuildConfig.BASE_PATH

    override suspend fun getHeaders(): MutableMap<String, String> =
        super.getHeaders().apply {
            put(PARAM_HEADER_LOCALE, preferencesRepository.getLocale().key)
            put(PARAM_DATETIME, parseDate(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss"))
            put(PARAM_USER_AGENT, buildUserAgentHeader())
            put(PARAM_TIMEZONE, TimeZone.getDefault().id)
            preferencesRepository.getToken()?.let {
                put(PARAM_HEADER_TOKEN, it)
            }
        }

    override suspend fun refreshToken() {
        userInteractor.refreshToken()
    }

    private fun buildUserAgentHeader(): String {
        val user = try {
            preferencesRepository.getUser()
        } catch (e: ErrorLoginException) {
            null
        }

        val map = mutableMapOf<String, String>()
        map[PARAM_VERSION] = BuildConfig.VERSION_NAME
        user?.let {
            map[PARAM_EMAIL] = it.email
            map[PARAM_USER_ID] = it.id
        }

        var userAgent = "$PARAM_ANDROID,"

        map.forEach {
            userAgent += "${it.key}:${it.value},"
        }

        return userAgent.dropLast(1)

    }

    companion object {
        // Headers constant
        const val PARAM_HEADER_LOCALE = "locale"
        const val PARAM_HEADER_TOKEN = "token"
        const val PARAM_DATETIME = "datetime"
        const val PARAM_TIMEZONE = "timezone"
        const val PARAM_USER_AGENT = "User-Agent"

        const val PARAM_USER_ID = "userId"
        const val PARAM_EMAIL = "email"
        const val PARAM_VERSION = "version"
        const val PARAM_ANDROID = "Android"
    }
}
