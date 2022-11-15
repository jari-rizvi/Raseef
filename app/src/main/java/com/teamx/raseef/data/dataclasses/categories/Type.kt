package com.teamx.raseef.dataclasses.categories

import androidx.annotation.Keep


@Keep
data class Type(
    val __v: Int,
    val _id: String,
    val banners: List<String>,
    val createdAt: String,
    val icon: String,
    val name: String,
    val promotional_sliders: List<String>,
    val settings: Settings,
    val slug: String,
    val updatedAt: String
)