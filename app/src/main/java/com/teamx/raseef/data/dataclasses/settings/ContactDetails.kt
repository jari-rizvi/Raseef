package com.teamx.raseef.dataclasses.settings

import androidx.annotation.Keep


@Keep
data class ContactDetails(
    val _id: String,
    val contact: String,
    val location: Location,
    val socials: List<Any>,
    val website: String
)