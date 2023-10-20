package com.training.app.app.ui.splash

import android.Manifest
import android.app.Activity
import android.os.Build
import android.os.Bundle
import com.github.florent37.runtimepermission.kotlin.askPermission
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.training.app.R
import com.training.app.app.ui.base.BaseActivity
import com.training.app.databinding.ActivitySplashBinding
import com.zappstudio.zappbase.app.ext.observeOnce
import com.zappstudio.zappbase.data.exception.ErrorLoginException
import com.zappstudio.zappbase.data.exception.UnsupportedVersionException
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivity() {

    private val binding: ActivitySplashBinding by lazy {
        ActivitySplashBinding.inflate(
            layoutInflater
        )
    }

    private val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Handle the splash screen transition.
        setContentView(binding.root)
        checkServicesAndSetup()
    }

    private fun checkServicesAndSetup() {
        checkGooglePlayServicesAvailability {
            viewModel.isSetupFinished.observeOnce(
                this,
                getResultEventObserver(action = {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        askPermission(Manifest.permission.POST_NOTIFICATIONS) {
                            startActivity(navigator.navigateToMain())
                        }.onDeclined {
                            startActivity(navigator.navigateToMain())
                        }
                    } else {
                        startActivity(navigator.navigateToMain())
                    }

                }, actionError = { throwable, _super ->
                    when (throwable) {
                        is UnsupportedVersionException -> _super(throwable)
                        is ErrorLoginException -> startActivity(navigator.navigateToMain())
                        else -> {
                            throwable.printStackTrace()
                            viewModel.logout().observe(
                                this,
                                getResultEventObserver({
                                    startActivity(navigator.navigateToMain())
                                }, { e, _ ->
                                    e.printStackTrace()
                                    startActivity(navigator.navigateToMain())
                                })
                            )
                        }
                    }
                })
            )
        }
    }

    private fun Activity.checkGooglePlayServicesAvailability(closure: () -> Unit) {
        val googleApiAvailability = GoogleApiAvailability.getInstance()
        val status = googleApiAvailability.isGooglePlayServicesAvailable(this)
        if (status != ConnectionResult.SUCCESS) {
            if (googleApiAvailability.isUserResolvableError(status)) {
                googleApiAvailability.getErrorDialog(this, status, 2404)?.show()
            } else {
                showPopup(getString(R.string.error),
                    getString(R.string.google_play_services_not_available),
                    positiveListener = { _, _ ->
                        finish()
                    })
            }
        }
        closure()
    }
}
