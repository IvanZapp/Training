package com.training.app.domain.model

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

data class ToolbarModel(
    var showBack: Boolean = false,
    var title: String? = null,
    var onBackPressed: (() -> Unit)? = null,
    var showImageProfile: Boolean = false,
    var imageProfile: String? = null,
    var imageProfileListener: (() -> Unit)? = null,
    var showImageProfileRight: Boolean = false,
    var imageProfileRight: String? = null,
    var imageProfileRightListener: (() -> Unit)? = null,
    @DrawableRes var rightIcon: Int? = null,
    @ColorRes var rightIconTint: Int? = null,
    var rightIconListener: (() -> Unit)? = null,
    @DrawableRes var secondaryRightIcon: Int? = null,
    @ColorRes var secondaryRightIconTint: Int? = null,
    @DrawableRes var badgeRightIcon: Int? = null,
    var secondaryRightIconListener: (() -> Unit)? = null,
    var showBadge: Boolean = false
)
