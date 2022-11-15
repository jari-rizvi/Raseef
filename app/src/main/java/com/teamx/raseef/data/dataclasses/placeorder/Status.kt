package com.teamx.raseef.dataclasses.placeorder

import androidx.annotation.Keep


@Keep
data class Status(
    val __v: Int,
    val _id: String,
    val color: String,
    val name: String,
    val serial: Int
)