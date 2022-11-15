package com.teamx.raseef.dataclasses.productid

import androidx.annotation.Keep


@Keep
data class Variation(
    val __v: Int,
    val _id: String,
    val attribute: Attribute,
    val createdAt: String,
    val meta: String,
    val updatedAt: String,
    val value: String
)