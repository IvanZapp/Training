package com.template.app.app.di

import com.template.app.data.repository.PreferencesRepository
import com.template.app.data.repository.UserRepository
import com.zappstudio.zappbase.app.di.qualifiers.Encrypted
import com.zappstudio.zappbase.app.di.qualifiers.Unencrypted
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module

val repositoryModule = module {

    single { PreferencesRepository(get(qualifier<Unencrypted>()), get(qualifier<Encrypted>()), get(), get()) }
    singleOf(::UserRepository)
}
