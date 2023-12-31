package com.template.app.app.di

import com.template.app.data.repository.network.ConfigurationRestClientImpl
import com.template.app.data.repository.network.api.UserApi
import com.zappstudio.webservice.BaseRequest
import com.zappstudio.webservice.model.ConfigurationRestClient
import org.koin.dsl.module

val apiModule = module {

    single<ConfigurationRestClient> { ConfigurationRestClientImpl(get()) }

    factory { get<BaseRequest>().retrofit.create(UserApi::class.java) }
}
