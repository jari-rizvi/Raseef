package com.teamx.raseef.dataclasses

import androidx.annotation.Keep

 
@Keep
data class AddressByID(
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