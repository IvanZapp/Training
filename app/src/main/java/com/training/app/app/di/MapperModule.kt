package com.training.app.app.di

import com.training.app.data.mapper.SessionMapper
import com.training.app.data.mapper.UserMapper
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val mapperModule = module {
    factoryOf(::UserMapper)
    factoryOf(::SessionMapper)
}
