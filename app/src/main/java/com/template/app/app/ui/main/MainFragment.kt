/*
 *
 *  * Copyright (c) 2022.
 *  * All rights reserved to Zapp Studio S.L
 *  * Av. de Zarandona, 29, 1 planta, 30007 Murcia
 *
 */

package com.template.app.app.ui.main

import android.content.Context
import com.template.app.app.ui.base.BaseFragment

abstract class MainFragment : BaseFragment(), MainListener {

    private lateinit var mainListener: MainListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainListener = when {
            activity is MainListener -> activity as MainListener
            parentFragment is MainListener -> parentFragment as MainListener
            else -> throw IllegalStateException("Parent activity or fragment must be ${MainListener::class.java.simpleName}")
        }
    }

    override fun changeFragment(option: MainMenuOptions) {
        mainListener.changeFragment(option)
    }
}
