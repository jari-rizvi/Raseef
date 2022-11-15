package com.teamx.raseef.dataclasses.productid

import androidx.annotation.Keep


@Keep
data class Attribute(
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val name: String,
    val shop: String,
    val slug: String,
    val updatedAt: String,
    val values: List<String>
)