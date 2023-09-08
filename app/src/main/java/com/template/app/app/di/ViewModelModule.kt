package com.template.app.app.di

import com.template.app.app.ui.login.LoginViewModel
import com.template.app.app.ui.main.MainViewModel
import com.template.app.app.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::SplashViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::MainViewModel)
}
