package com.teamx.raseef.dataclasses.settings

import androidx.annotation.Keep


@Keep
data class Location(
    val _id: String,
    val city: String,
    val country: String,
    val formattedAddress: String,
    val lat: Double,
    val lng: Double,
    val state: String
)