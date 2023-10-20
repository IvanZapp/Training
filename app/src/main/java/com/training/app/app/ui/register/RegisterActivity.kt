package com.training.app.app.ui.register

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.training.app.app.ui.base.BaseActivity
import com.training.app.databinding.ActivityRegisterBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : BaseActivity() {

    private val binding: ActivityRegisterBinding by lazy {
        ActivityRegisterBinding.inflate(layoutInflater)
            .apply {
                lifecycleOwner = this@RegisterActivity
            }
    }

    private val viewModel: RegisterViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideToolbar()
        setContentView(binding.root)
    }

    inner class Handler {
        fun register() {
            // TODO comprobar campos obligatorios
            showLoading()
            viewModel.register().observe(
                this@RegisterActivity,
                getResultEventObserver({
                    hideLoading()
                })
            )
        }
    }

    companion object {
        fun getCallingIntent(context: Context): Intent =
            Intent(context, RegisterActivity::class.java)
    }
}
