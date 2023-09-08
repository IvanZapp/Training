package com.template.app.app.ui

import android.content.Context
import android.content.Intent
import com.template.app.app.ui.login.LoginActivity
import com.template.app.app.ui.main.MainActivity
import com.template.app.app.ui.register.RegisterActivity
import com.zappstudio.databinding.ui.context.ZappBaseNavigator

class Navigator(context: Context) : ZappBaseNavigator(context) {
    override fun navigateToLogin(): Intent = LoginActivity.getCallingIntent(context).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    fun navigateToRegister(): Intent = RegisterActivity.getCallingIntent(context)
    fun navigateToMain(): Intent = MainActivity.getCallingIntent(context).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
}
