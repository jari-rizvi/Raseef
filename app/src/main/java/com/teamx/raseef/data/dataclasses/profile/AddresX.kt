package com.teamx.raseef.dataclasses.profile

import com.teamx.raseef.dataclasses.Address

import androidx.annotation.Keep


@Keep
data class AddresX(
    val __v: Int,
    val _id: String,
    val address: Address,
    val createdAt: String,
    val customer: String,
    val default: Boolean,
    val title: String,
    val type: String,
    val updatedAt: String
)