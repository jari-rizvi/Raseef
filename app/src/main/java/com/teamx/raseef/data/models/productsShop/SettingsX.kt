package com.teamx.raseef.data.models.productsShop

import androidx.annotation.Keep


@Keep
data class SettingsX(
    val isHome: Boolean,
    val layoutType: String,
    val productCard: String
)