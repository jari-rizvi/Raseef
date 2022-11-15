package com.teamx.raseef.dataclasses.customeraddress

import com.teamx.raseef.dataclasses.Address

import androidx.annotation.Keep


@Keep
data class Doc(
    val __v: Int,
    val _id: String,
    val address: Address,
    val createdAt: String,
    val customer: Customer,
    val default: Boolean,
    val title: String,
    val type: String,
    val updatedAt: String
)