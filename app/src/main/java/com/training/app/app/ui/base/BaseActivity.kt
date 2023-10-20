package com.training.app.app.ui.base

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContracts
import com.training.app.R
import com.training.app.app.ui.Navigator
import com.zappstudio.databinding.ui.context.ZappBaseToolbarActivity
import com.zappstudio.databinding.ui.webview.PrivacyPolicyActivity
import com.zappstudio.picker.exception.PickerError
import com.zappstudio.zappbase.data.exception.PrivacyPolicyMustBeAccepted
import com.zappstudio.zappbase.data.exception.UnsupportedVersionException
import com.zappstudio.zappbase.domain.model.RemotePrivacyPolicy
import org.koin.android.ext.android.inject

abstract class BaseActivity : ZappBaseToolbarActivity(), BaseListener {

    override val navigator: Navigator by inject()

    private val privacyPolicyResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_CANCELED) {
            finish()
        } else {
            onPrivacyPolicyAccepted(it.data?.getParcelableExtra(PrivacyPolicyActivity.POLICY_EXTRA)!!)
        }
    }

    open fun onPrivacyPolicyAccepted(parcelableExtra: RemotePrivacyPolicy) {
    }


    override fun onError(throwable: Throwable) {
        when (throwable) {
            is UnsupportedVersionException -> {
                showPopup(
                    getString(R.string.error),
                    getString(R.string.must_update_app),
                    positiveListener = { _, _ ->
                        throwable.remoteConfigVersionModel?.let {
                            startActivity(navigator.navigateToWeb(it.urlStoreAndroid))
                        }
                    }, cancelable = false
                )
            }
            is PrivacyPolicyMustBeAccepted -> {
                showPopup(
                    getString(R.string.warning),
                    getString(R.string.privacy_policy_must_be_accepted),
                    positiveListener = { _, _ ->
                        privacyPolicyResult.launch(navigator.navigateToPrivacyPolicy(getString(R.string.privacy_policy), throwable.remoteConfigVersionModel))
                    }, cancelable = false
                )

            }
            else -> super.onError(throwable)
        }
    }

    override fun onErrorDefaultCase(throwable: Throwable, onDismiss: () -> Unit) {
        if (throwable is PickerError) {
            showPopup(
                title = "",
                description = "",
                positiveButton = "",
                positiveListener = { _, _ ->
                    val intent= Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package", this.packageName, null)
                    intent.data = uri
                    startActivity(intent)
                },
                negativeButton = "",
                cancelable = false
            )
        } else {
            super.onErrorDefaultCase(throwable, onDismiss)
        }
    }
}
