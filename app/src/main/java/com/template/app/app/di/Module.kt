package com.template.app.app.di

import com.template.app.BuildConfig
import com.template.app.app.ui.Navigator
import com.template.app.app.ui.crashlytics.CrashReportingTree
import com.zappstudio.zappbase.domain.model.InfoAppModel
import org.koin.dsl.module
import timber.log.Timber

val appModule = module {

    single<Timber.Tree> {
        CrashReportingTree(get())
    }

    single {
        Navigator(get())
    }

    single {
        InfoAppModel(BuildConfig.VERSION_CODE, BuildConfig.VERSION_NAME, BuildConfig.FLAVOR, "zappbase","template_privacy_policy")
    }
}
