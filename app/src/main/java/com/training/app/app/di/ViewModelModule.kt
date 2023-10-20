package com.training.app.app.di

import com.training.app.app.ui.login.LoginViewModel
import com.training.app.app.ui.main.MainViewModel
import com.training.app.app.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::SplashViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::MainViewModel)
}
