package com.training.app.app.ui.base

import android.annotation.SuppressLint
import android.content.Context
import com.training.app.app.ui.Navigator
import com.zappstudio.databinding.ui.context.ZappBaseToolbarFragment
import org.koin.android.ext.android.inject

abstract class BaseFragment : ZappBaseToolbarFragment(), BaseListener {

    override val navigator: Navigator by inject()

    private lateinit var listener: BaseListener

    @SuppressLint("MissingSuperCall")
    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = when {
            context is BaseListener -> context
            parentFragment is BaseListener -> parentFragment as BaseListener
            else -> throw IllegalStateException("No ZappBaseListener attached!")
        }
    }
}
