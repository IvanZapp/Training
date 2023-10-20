/*
 *
 *  * Copyright (c) 2022.
 *  * All rights reserved to Zapp Studio S.L
 *  * Av. de Zarandona, 29, 1 planta, 30007 Murcia
 *
 */

package com.training.app.app.ui.main

import android.os.Parcelable
import androidx.annotation.IdRes
import com.training.app.R
import com.training.app.app.ui.home.HomeFragment
import kotlinx.parcelize.Parcelize

@Parcelize
enum class MainMenuOptions(
    @IdRes val menuId: Int,
    val fragment: () -> MainFragment
) : Parcelable {

    HOME(R.id.menu_delete, { HomeFragment() }),
    EVENTS(R.id.menu_add, { HomeFragment() }),
    SOCIAL(R.id.menu_delete, { HomeFragment() }),
    PROFILE(R.id.menu_delete, { HomeFragment() });

    companion object {
        fun findByMenuId(@IdRes menuId: Int) = values().find { it.menuId == menuId }
    }
}
