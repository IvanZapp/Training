package com.training.app.app.di

import com.training.app.domain.interactor.UserInteractor
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val interactorModule = module {
    factoryOf(::UserInteractor)
}
