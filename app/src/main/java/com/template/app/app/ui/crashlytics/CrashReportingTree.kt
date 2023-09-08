package com.template.app.app.ui.crashlytics

import com.template.app.data.repository.PreferencesRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

class CrashReportingTree(
    private val preferencesRepository: PreferencesRepository
) : Timber.Tree() {

    init {
        GlobalScope.launch {
            preferencesRepository.observeUser().collect {
                it?.let {
                    CrashReports.setUserIdentifier(it.id)
                }
            }
        }
    }

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority != android.util.Log.ERROR)
            return

        t?.let {
            CrashReports.logException(t)
        }
    }
}
