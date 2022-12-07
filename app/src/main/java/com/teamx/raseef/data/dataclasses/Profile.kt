package com.teamx.raseef.data.dataclasses

import androidx.annotation.Keep


@Keep
data class Profile(
    val _id: String,
    val avatar: String,
    val bio: String
)