package com.training.app.app.ui.login

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import com.github.florent37.runtimepermission.kotlin.askPermission
import com.training.app.app.ui.base.BaseActivity
import com.training.app.databinding.ActivityLoginBinding
import com.zappstudio.zappbase.app.ext.observeOnce
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity() {

    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(
            layoutInflater
        ).apply {
            lifecycleOwner = this@LoginActivity
            viewModel = this@LoginActivity.viewModel
            handler = Handler()
        }
    }

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideToolbar()
        setContentView(binding.root)
    }

    inner class Handler {
        fun login() {
            // TODO comprobar campos obligatorios
            showLoading()
            viewModel.login()
                .observeOnce(
                    this@LoginActivity,
                    getResultObjectObserver(action = {
                        hideLoading()
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            askPermission(Manifest.permission.POST_NOTIFICATIONS) {
                                startActivity(navigator.navigateToMain())
                            }.onDeclined {
                                startActivity(navigator.navigateToMain())
                            }
                        } else {
                            startActivity(navigator.navigateToMain())
                        }
                    })
                )
        }

        fun register() {
            startActivity(navigator.navigateToRegister())
        }
    }

    companion object {
        fun getCallingIntent(context: Context): Intent =
            Intent(context, LoginActivity::class.java)
    }
}
