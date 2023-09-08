package com.template.app.app.di

import com.template.app.data.mapper.SessionMapper
import com.template.app.data.mapper.UserMapper
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val mapperModule = module {
    factoryOf(::UserMapper)
    factoryOf(::SessionMapper)
}
