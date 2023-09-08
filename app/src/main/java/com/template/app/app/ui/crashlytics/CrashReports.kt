package com.template.app.app.ui.crashlytics

import com.google.firebase.crashlytics.FirebaseCrashlytics

object CrashReports {

    @JvmStatic
    fun logException(throwable: Throwable?) {
        try {
            FirebaseCrashlytics.getInstance().recordException(throwable!!)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    @JvmStatic
    fun setString(key: String, value: String) {
        FirebaseCrashlytics.getInstance().setCustomKey(key, value)
    }

    @JvmStatic
    fun setBoolean(key: String?, value: Boolean) {
        FirebaseCrashlytics.getInstance().setCustomKey(key!!, value)
    }

    fun setInt(key: String, value: Int) {
        FirebaseCrashlytics.getInstance().setCustomKey(key, value)
    }

    fun setUserIdentifier(userId: String) {
        FirebaseCrashlytics.getInstance().setUserId(userId)
    }
}
