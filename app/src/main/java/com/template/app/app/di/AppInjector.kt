package com.template.app.app.di

import com.zappstudio.webservice.di.webServiceModule
import com.zappstudio.zappbase.gms.di.BaseInjectorGms
import org.koin.core.module.Module

class AppInjector : BaseInjectorGms() {

    override fun getKoinModules(): MutableList<Module> =
        mutableListOf<Module>().apply {
            add(webServiceModule)
            add(interactorModule)
            add(repositoryModule)
            add(mapperModule)
            add(viewModelModule)
            add(apiModule)
            add(appModule)
        }
}
