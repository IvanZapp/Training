package com.template.app.app

import android.annotation.SuppressLint
import com.template.app.app.di.AppInjector
import com.zappstudio.zappbase.app.di.BaseInjector
import com.zappstudio.zappbase.gms.app.ui.context.ZappBaseGmsApplication
import org.koin.android.ext.android.get
import timber.log.Timber

class App : ZappBaseGmsApplication() {

    @SuppressLint("MissingSuperCall")
    override fun onCreate() {
        super.onCreate()
        Timber.plant(if (com.template.app.BuildConfig.DEBUG) Timber.DebugTree() else getTimberTree())
    }

    private fun getTimberTree(): Timber.Tree =
        get()

    override fun getInjector(): BaseInjector = AppInjector()
}
